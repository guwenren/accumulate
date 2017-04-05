package com.guwr.accumulate.service.authority;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class DubboProvider {
	private static Logger LOGGER = LoggerFactory.getLogger(DubboProvider.class);

	public static void main(String[] args) {
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
			LOGGER.info("context = " + context);
			LOGGER.info("accumulate-service-authority");
			context.start();
		} catch (Exception e) {
			LOGGER.error("== DubboProvider context start error:", e);
		}

		synchronized (DubboProvider.class) {
			while (true) {
				try {
					DubboProvider.class.wait();
				} catch (InterruptedException e) {
					LOGGER.error("== synchronized error:", e);
				}
			}
		}
	}

}