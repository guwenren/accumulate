package com.guwr.accumulate.service.job.util;

import com.guwr.accumulate.service.job.callback.JobLogCallbackServer;
import com.guwr.accumulate.service.job.core.service.IQrtzTriggerInfoService;
import com.guwr.accumulate.service.job.core.service.IQrtzTriggerLogService;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.job.util.DynamicSchedulerUtils
 * Date 2016/9/18
 * Time 15:43
 */
@Component
public class DynamicSchedulerUtils implements ApplicationContextAware, Serializable {
    private static Logger logger = LoggerFactory.getLogger(DynamicSchedulerUtils.class);
    private Scheduler scheduler;

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    // trigger callback port
    private int callBackPort;

    public void setCallBackPort(int callBackPort) {
        this.callBackPort = callBackPort;
    }

    // init
    private JobLogCallbackServer server = null;

    public void init() {
        logger.info("DynamicSchedulerUtils.init");
        try {
            server = new JobLogCallbackServer();
            server.start(callBackPort);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        logger.info("DynamicSchedulerUtils.destroy");
        if (server != null) {
            server.destroy();
        }
    }

    public static IQrtzTriggerInfoService qrtzTriggerInfoService;
    public static IQrtzTriggerLogService qrtzTriggerLogService;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        DynamicSchedulerUtils.qrtzTriggerInfoService = applicationContext.getBean(IQrtzTriggerInfoService.class);
        DynamicSchedulerUtils.qrtzTriggerLogService = applicationContext.getBean(IQrtzTriggerLogService.class);
    }
}
