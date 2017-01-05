package com.guwr.accumulate.service.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class DubboProviderServiceAccount {
	private static Logger logger = LoggerFactory.getLogger(DubboProviderServiceAccount.class);

	public static void main(String[] args) {
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
			logger.info("accumulate-service-account");
			logger.info("context = " + context);
			context.start();
		} catch (Exception e) {
			logger.error("== DubboProviderServiceAccount context start error:", e);
		}

		synchronized (DubboProviderServiceAccount.class) {
			while (true) {
				try {
					DubboProviderServiceAccount.class.wait();
				} catch (InterruptedException e) {
					logger.error("== synchronized error:", e);
				}
			}
		}
	}
}