package com.guwr.accumulate.service.notify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class DubboProviderServiceNotify {
	private static Logger logger = LoggerFactory.getLogger(DubboProviderServiceNotify.class);

	public static void main(String[] args) {
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
			logger.info("accumulate-service-notify");
			logger.info("context = " + context);
			context.start();
		} catch (Exception e) {
			logger.error("== DubboProviderServiceNotify context start error:", e);
		}
		synchronized (DubboProviderServiceNotify.class) {
			while (true) {
				try {
					DubboProviderServiceNotify.class.wait();
				} catch (InterruptedException e) {
					logger.error("== synchronized error:", e);
				}
			}
		}
	}
}