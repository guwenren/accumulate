package com.guwr.accumulate.queue.notify.message;

import com.alibaba.fastjson.JSON;
import com.guwr.accumulate.facade.notify.entity.NotifyRecord;
import com.guwr.accumulate.facade.notify.enums.NotifyRecordEnum;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 *
 */
@Component
public class ConsumerSessionAwareMessageListener implements SessionAwareMessageListener<Message> {
    private static Logger logger = LoggerFactory.getLogger(ConsumerSessionAwareMessageListener.class);

    @Override
    public void onMessage(Message message, Session session) throws JMSException {
        ActiveMQTextMessage textMessage = (ActiveMQTextMessage) message;
        String msgText = textMessage.getText();
        logger.info("msgText = " + msgText);
        NotifyRecord notifyRecord = JSON.parseObject(msgText, NotifyRecord.class);
        logger.info("notifyRecord = " + notifyRecord);
        //创建通知记录
        notifyRecord.setStatus(NotifyRecordEnum.NotifyRecordEnumStatus.CREATED.getValue());
    }
}
