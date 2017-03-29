package com.guwr.accumulate.queue.notify.message;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.queue.notify.message.TestMessageListener
 * Date         2016/11/25
 * Time         11:38
 * Description  消息测试
 */
@Component
public class TestMessageListener implements SessionAwareMessageListener<Message> {

    private static Logger logger = LoggerFactory.getLogger(TestMessageListener.class);


    @Override
    public void onMessage(Message message, Session session) throws JMSException {
        ActiveMQTextMessage textMessage = (ActiveMQTextMessage) message;
        String msgText = textMessage.getText();
        logger.info("msgText = " + msgText);
    }
}
