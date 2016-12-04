package com.guwr.accumulate.queue.notify;

import com.guwr.accumulate.queue.notify.message.scheduled.IMessageScheduled;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.queue.notify.MessageTask
 * Date         2016/11/26
 * Time         16:19
 * Description  消息状态确认
 */
public class MessageTask {

    private static Logger logger = LoggerFactory.getLogger(MessageTask.class);

    public static void main(String[] args) {
        System.out.println("MessageTask.main");
        init();
    }

    private static void init() {
        final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:spring/spring-context.xml"});
        context.start();
        logger.info("定时任务开始执行>>>");
        ThreadPoolTaskExecutor threadPool = (ThreadPoolTaskExecutor) context.getBean("threadPool");
        final IMessageScheduled messageScheduled = (IMessageScheduled) context.getBean("messageScheduled");
        System.out.println("threadPool = " + threadPool);

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    logger.info("执行(处理[waiting_confirm]状态的消息)任务开始");
                    messageScheduled.handleWaitingConfirmTimeOutMessages();
                    logger.info("执行(处理[waiting_confirm]状态的消息)任务结束");
                }
            }
        });
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    logger.info("执行(处理[already_confirm]状态的消息)任务开始");
                    messageScheduled.handleAlreadyConfirmTimeOutMessages();
                    logger.info("执行(处理[already_confirm]状态的消息)任务结束");
                }
            }
        });
    }
}
