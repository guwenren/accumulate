package com.guwr.accumulate.service.job.jobbean;

import com.guwr.accumulate.common.util.CommonUtils;
import com.guwr.accumulate.facade.job.entity.QrtzTriggerInfo;
import com.guwr.accumulate.facade.job.entity.QrtzTriggerLog;
import com.guwr.accumulate.facade.job.router.model.RequestModel;
import com.guwr.accumulate.facade.job.router.model.ResponseModel;
import com.guwr.accumulate.facade.job.util.RequestUtils;
import com.guwr.accumulate.service.job.callback.JobLogCallbackServer;
import com.guwr.accumulate.service.job.thread.JobMonitorHelper;
import com.guwr.accumulate.service.job.util.DynamicSchedulerUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Date;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.job.jobbean.RemoteHttpJobBean
 * Date 2016/9/19
 * Time 20:09
 */
@Component
public class RemoteHttpJobBean extends QuartzJobBean {

    private static Logger logger = LoggerFactory.getLogger(RemoteHttpJobBean.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("RemoteHttpJobBean.execute");
        JobKey jobKey = jobExecutionContext.getTrigger().getJobKey();
        String group = jobKey.getGroup();
        String name = jobKey.getName();

        QrtzTriggerInfo qrtzTriggerInfo = DynamicSchedulerUtils.qrtzTriggerInfoService.findOneByGroupAndName(group, name);
        QrtzTriggerLog qrtzTriggerLog = getQrtzTriggerLog(qrtzTriggerInfo);
        Integer jobLogId = qrtzTriggerLog.getId();

        logger.info("请求日志开始，{}", CommonUtils.obj2Json(qrtzTriggerLog));
        RequestModel requestModel = getRequestModel(qrtzTriggerInfo, jobLogId);

        ResponseModel responseModel = failoverTrigger(qrtzTriggerInfo.getExecutorAddress(), requestModel, qrtzTriggerLog);

        update(qrtzTriggerInfo, qrtzTriggerLog, responseModel);

        JobMonitorHelper.monitor(jobLogId);
    }

    /**
     * 组装请求Request
     * @param qrtzTriggerInfo
     * @param jobLogId
     * @return
     */
    private RequestModel getRequestModel(QrtzTriggerInfo qrtzTriggerInfo, Integer jobLogId) {
        logger.info("RemoteHttpJobBean.getRequestModel");
        RequestModel requestModel = new RequestModel();
        requestModel.setTimestamp(System.currentTimeMillis());
        requestModel.setAction("RUN");
        requestModel.setJobGroup(qrtzTriggerInfo.getJobGroup());
        requestModel.setJobName(qrtzTriggerInfo.getJobName());
        requestModel.setExecutorHandler(qrtzTriggerInfo.getExecutorHandler());
        requestModel.setExecutorParams(qrtzTriggerInfo.getExecutorParam());
        requestModel.setGlueSwitch(false);
        requestModel.setLogAddress(JobLogCallbackServer.getTrigger_log_address());
        requestModel.setLogId(jobLogId);
        return requestModel;
    }

    /**
     * 调用前保存请求日志
     *
     * @param qrtzTriggerInfo
     * @return
     */
    private QrtzTriggerLog getQrtzTriggerLog(QrtzTriggerInfo qrtzTriggerInfo) {
        logger.info("RemoteHttpJobBean.getQrtzTriggerLog");
        QrtzTriggerLog qrtzTriggerLog = new QrtzTriggerLog();
        qrtzTriggerLog.setJobGroup(qrtzTriggerInfo.getJobGroup());
        qrtzTriggerLog.setJobName(qrtzTriggerInfo.getJobName());
        qrtzTriggerLog = DynamicSchedulerUtils.qrtzTriggerLogService.save(qrtzTriggerLog);
        return qrtzTriggerLog;
    }

    /**
     * 修改日志请求结果
     *
     * @param qrtzTriggerInfo
     * @param qrtzTriggerLog
     * @param responseModel
     */
    private void update(QrtzTriggerInfo qrtzTriggerInfo, QrtzTriggerLog qrtzTriggerLog, ResponseModel responseModel) {
        logger.info("RemoteHttpJobBean.update");
        qrtzTriggerLog.setExecutorHandler(qrtzTriggerInfo.getExecutorHandler());
        qrtzTriggerLog.setExecutorParam(qrtzTriggerInfo.getExecutorParam());
        qrtzTriggerLog.setTriggerTime(new Date());
        qrtzTriggerLog.setTriggerStatus(responseModel.getStatus());
        qrtzTriggerLog.setTriggerMsg(responseModel.getMsg());
        DynamicSchedulerUtils.qrtzTriggerLogService.updateTriggerInfo(qrtzTriggerLog);
    }

    private ResponseModel failoverTrigger(String executorAddress, RequestModel requestModel, QrtzTriggerLog qrtzTriggerLog) {
        logger.info("RemoteHttpJobBean.failoverTrigger");
        qrtzTriggerLog.setExecutorAddress(executorAddress);
        String reqURL = "http://" + executorAddress + "/";
        ResponseModel responseModel = RequestUtils.postHex(reqURL, requestModel);
        String status = responseModel.getStatus();
        String msg = responseModel.getMsg();
        String failoverMessage = MessageFormat.format("Trigger running, <br>>>>[address] : {0}, <br>>>>[status] : {1}, <br>>>>[msg] : {2} <br><hr>", executorAddress, status, msg);
        responseModel.setMsg(failoverMessage);
        return responseModel;
    }
}