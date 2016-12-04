package com.guwr.accumulate.facade.job.router.action;

import com.guwr.accumulate.facade.job.handler.IJobHandler;
import com.guwr.accumulate.facade.job.router.HandlerRouter;
import com.guwr.accumulate.facade.job.router.IAction;
import com.guwr.accumulate.facade.job.router.model.RequestModel;
import com.guwr.accumulate.facade.job.router.model.ResponseModel;
import com.guwr.accumulate.facade.job.router.thread.JobThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.job.router.action.RunAction
 * Date 2016/9/27
 * Time 13:38
 */
public class RunAction extends IAction {

    private static Logger logger = LoggerFactory.getLogger(RunAction.class);

    @Override
    public ResponseModel execute(RequestModel requestModel) {
        logger.info("RunAction.execute");
        String jobGroup = requestModel.getJobGroup();
        String jobName = requestModel.getJobName();
        String jobKey = jobGroup.concat("_").concat(jobName);

        JobThread jobThread = HandlerRouter.loadJobThread(jobKey);
        boolean glueSwitch = requestModel.isGlueSwitch();
        if (glueSwitch) {

        } else {
            // 根据配置 ExecutorHandler 获取需要执行的job
            IJobHandler jobHandler = HandlerRouter.loadJobHandler(requestModel.getExecutorHandler());
            if (jobHandler == null) {
                return new ResponseModel(ResponseModel.FAIL, "job handler for jobKey=[" + jobKey + "] not found.");
            }
            if (jobThread == null) {
                jobThread = HandlerRouter.registJobThread(jobKey, jobHandler);
            }
        }
        jobThread.pushTriggerQueue(requestModel);
        return new ResponseModel(ResponseModel.SUCCESS, null);
    }
}
