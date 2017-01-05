package com.guwr.accumulate.service.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class DubboProviderServiceUser {
	private static Logger logger = LoggerFactory.getLogger(DubboProviderServiceUser.class);

	public static void main(String[] args) {
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
			logger.info("accumulate-service-user");
			logger.info("context = " + context);
			context.start();
		} catch (Exception e) {
			logger.error("== DubboProviderServiceUser context start error:", e);
		}

		synchronized (DubboProviderServiceUser.class) {
			while (true) {
				try {
					DubboProviderServiceUser.class.wait();
				} catch (InterruptedException e) {
					logger.error("== synchronized error:", e);
				}
			}
		}
	}

}