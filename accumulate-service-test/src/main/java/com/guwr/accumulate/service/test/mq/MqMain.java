package com.guwr.accumulate.service.test.mq;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by guwr
 * Project_name accumulate
 * Path com.guwr.accumulate.service.test.mq.MqMain
 * Date 2017/3/29
 * Time 10:21
 * Description
 */
public class MqMain {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
        System.out.println("context = " + context);
        send(context);
    }

    private static void send(ClassPathXmlApplicationContext context) {
        JmsTemplate jmsTemplate = context.getBean("jmsTemplate", JmsTemplate.class);

        System.out.println("jmsTemplate = " + jmsTemplate);
        for (int i = 0; i < 3; i++) {
            final String json = "mqTest" + i;
            jmsTemplate.send(new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    return session.createTextMessage(json);
                }
            });
        }
    }
}
