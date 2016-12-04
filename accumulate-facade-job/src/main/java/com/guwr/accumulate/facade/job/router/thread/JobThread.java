package com.guwr.accumulate.facade.job.router.thread;

import com.guwr.accumulate.facade.job.handler.IJobHandler;
import com.guwr.accumulate.facade.job.router.model.RequestModel;
import com.guwr.accumulate.facade.job.router.model.ResponseModel;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.util.ConcurrentHashSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by gwr
 * Description 执行job的线程
 * Path com.guwr.accumulate.facade.job.router.thread.JobThread
 * Date 2016/9/27
 * Time 11:50
 */
public class JobThread extends Thread {
    private static Logger logger = LoggerFactory.getLogger(JobThread.class);

    private IJobHandler handler;
    private LinkedBlockingQueue<RequestModel> triggerQueue;
    private ConcurrentHashSet<Integer> triggerLogIdSet;

    private boolean toStop = false;//停止
    private String stopReason;//停止原因

    public JobThread(IJobHandler jobHandler) {
        logger.info("JobThread.JobThread");
        this.handler = jobHandler;
        triggerQueue = new LinkedBlockingQueue<>();
        triggerLogIdSet = new ConcurrentHashSet<>();
    }
    @Override
    public void run() {
        logger.info("JobThread.run");
        while (!toStop) {
            try {
                RequestModel requestModel = triggerQueue.poll(3L, TimeUnit.SECONDS);
                if (requestModel != null) {
                    int logId = requestModel.getLogId();
                    triggerLogIdSet.remove(logId);
                    String[] params = null;
                    String executorParams = requestModel.getExecutorParams();
                    if (StringUtils.isNotBlank(executorParams)) {
                        params = StringUtils.split(",");
                    }
                    // handle job
                    String _status = ResponseModel.SUCCESS;
                    String _msg = null;
                    try {
                        logger.info("----------- xxl-job job handle start -----------");
                        handler.execute(params);
                    } catch (Exception e) {
                        logger.info("JobThread Exception:", e);
                        _status = ResponseModel.FAIL;
                        StringWriter out = new StringWriter();
                        e.printStackTrace(new PrintWriter(out));
                        _msg = out.toString();
                    }
                    // callback handler info
                    if (!toStop) {
                        // commonm
                        requestModel.setStatus(_status);
                        requestModel.setMsg(_msg);
                        TriggerCallbackThread.pushCallBack(requestModel);
                    } else {
                        // is killed
                        requestModel.setStatus(ResponseModel.FAIL);
                        requestModel.setMsg(stopReason + " [业务运行中，被强制终止]");
                        TriggerCallbackThread.pushCallBack(requestModel);
                    }
                }
            } catch (Exception e) {
                logger.info("JobThread Exception:", e);
            }
        }
    }

    public void pushTriggerQueue(RequestModel requestModel) {
        logger.info("JobThread.pushTriggerQueue");
        if (triggerLogIdSet.contains(requestModel.getLogId())) {
            logger.info("repeate trigger job, logId:{}", requestModel.getLogId());
            return;
        }
        triggerLogIdSet.add(requestModel.getLogId());
        triggerQueue.add(requestModel);
    }
}
