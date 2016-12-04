package com.guwr.accumulate.queue.notify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class DubboProvider {
    private static Logger logger = LoggerFactory.getLogger(DubboProvider.class);

    public static void main(String[] args) {
        try {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
            logger.info("accumulate-queue-notify");
            logger.info("context = " + context);
            context.start();
//            NotifyParam notifyParam = context.getBean("notifyParam", NotifyParam.class);
//            System.out.println("notifyParam = " + notifyParam);
        } catch (Exception e) {
            logger.error("== DubboProvider context start error:", e);
        }

        synchronized (DubboProvider.class) {
            while (true) {
                try {
                    DubboProvider.class.wait();
                } catch (InterruptedException e) {
                    logger.error("== synchronized error:", e);
                }
            }
        }
    }

}