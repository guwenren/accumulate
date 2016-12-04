package com.guwr.accumulate.facade.job.router;

import com.guwr.accumulate.facade.job.handler.IJobHandler;
import com.guwr.accumulate.facade.job.router.thread.JobThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by gwr
 * Description 保存初始化的所有Job，以及执行Job的线程
 * Path com.guwr.accumulate.facade.job.router.HandlerRouter
 * Date 2016/9/27
 * Time 11:48
 */
public class HandlerRouter {

    private static Logger logger = LoggerFactory.getLogger(HandlerRouter.class);
    /**
     * job handler repository
     */
    private static ConcurrentHashMap<String, IJobHandler> jobHandlerRepository = new ConcurrentHashMap<>();
    /**
     * job thread repository
     */
    private static ConcurrentHashMap<String, JobThread> jobThreadRepository = new ConcurrentHashMap<>();

    public static IJobHandler loadJobHandler(String executorHandler) {
        logger.info("HandlerRouter.loadJobHandler");
        return HandlerRouter.jobHandlerRepository.get(executorHandler);
    }

    public static JobThread loadJobThread(String jobKey) {
        logger.info("HandlerRouter.loadJobThread");
        return HandlerRouter.jobThreadRepository.get(jobKey);
    }

    public static JobThread registJobThread(String jobKey, IJobHandler jobHandler) {
        logger.info("HandlerRouter.registJobThread");
        JobThread jobThread = new JobThread(jobHandler);
        jobThread.start();
        logger.info(">>>>>>>>>>> xxl-job regist JobThread success, jobkey:{}, handler:{}", new Object[]{jobKey, jobHandler});
        HandlerRouter.jobThreadRepository.put(jobKey, jobThread);
        return jobThread;
    }

    public static IJobHandler registJobHandler(String name, IJobHandler jobHandler) {
        logger.info("HandlerRouter.registJobHandler");
        return HandlerRouter.jobHandlerRepository.put(name, jobHandler);
    }
}
