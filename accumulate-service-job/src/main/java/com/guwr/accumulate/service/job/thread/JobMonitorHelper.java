package com.guwr.accumulate.service.job.thread;

import com.guwr.accumulate.facade.job.entity.QrtzTriggerLog;
import com.guwr.accumulate.facade.job.router.model.ResponseModel;
import com.guwr.accumulate.service.job.util.DynamicSchedulerUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by gwr
 * Description   便利所有请求日志，如果调用失败，发送站内信
 * Path com.guwr.accumulate.service.job.thread.JobMonitorHelper
 * Date 2016/9/23
 * Time 11:37
 */
public class JobMonitorHelper implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(JobMonitorHelper.class);
    private static JobMonitorHelper helper = new JobMonitorHelper();
    private ExecutorService executor = Executors.newCachedThreadPool();
    private LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(0xfff8);

    public JobMonitorHelper() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Integer jobLogId = JobMonitorHelper.helper.queue.take();
                        if (jobLogId == null || jobLogId < 1) {
                            logger.info(">>>>>>>>>> jobLogId is null");
                            continue;
                        }
                        QrtzTriggerLog qrtzTriggerLog = DynamicSchedulerUtils.qrtzTriggerLogService.findOne(jobLogId);
                        if (qrtzTriggerLog == null) {
                            logger.info(">>>>>>>>>> qrtzTriggerLog is null");
                            continue;
                        }
                        // 根据请求日志判断如何处理
                        String triggerStatus = qrtzTriggerLog.getTriggerStatus();
                        String handleStatus = qrtzTriggerLog.getHandleStatus();

                        if (ResponseModel.SUCCESS.equals(triggerStatus) && StringUtils.isBlank(handleStatus)) {
                            logger.info(">>>>>>>>>> 远程调用成功，但任务还未完成，等待10秒，将该日志重新加入队列，待会再调用");
                            TimeUnit.SECONDS.sleep(10);
                            JobMonitorHelper.monitor(jobLogId);
                        } else {
                            if (ResponseModel.FAIL.equals(triggerStatus) || ResponseModel.FAIL.equals(handleStatus)) {
                                logger.info(">>>>>>>>>> 任务调用失败，发送邮件通知");
                                // TODO 发送邮件通知
                            }
                        }
                        logger.info("jobLogId = " + jobLogId);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    // producer
    public static void monitor(int jobLogId) {
        JobMonitorHelper.helper.queue.offer(jobLogId);
    }
}
