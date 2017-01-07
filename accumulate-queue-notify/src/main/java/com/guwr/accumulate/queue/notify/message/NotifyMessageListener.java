package com.guwr.accumulate.queue.notify.message;

import com.alibaba.fastjson.JSON;
import com.guwr.accumulate.facade.notify.facade.INotifyMessageFacade;
import com.guwr.accumulate.facade.notify.facade.INotifyTransactionMessageFacade;
import com.guwr.accumulate.facade.notify.vo.NotifyMessageVO;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.queue.notify.message.NotifyMessageListener
 * Date         2016/11/25
 * Time         11:38
 * Description
 */
@Component
public class NotifyMessageListener implements SessionAwareMessageListener<Message> {

    private static Logger logger = LoggerFactory.getLogger(NotifyMessageListener.class);

    @Autowired
    private INotifyMessageFacade notifyMessageFacade;

    @Autowired
    private INotifyTransactionMessageFacade notifyTransactionMessageFacade;

    @Override
    public void onMessage(Message message, Session session) throws JMSException {
        logger.info("NotifyMessageListener.onMessage.message = [" + message + "], session = [" + session + "]");
        ActiveMQTextMessage textMessage = (ActiveMQTextMessage) message;
        String msgText = textMessage.getText();
        logger.info("msgText = " + msgText);
        NotifyMessageVO info = JSON.parseObject(msgText, NotifyMessageVO.class);
        notifyMessageFacade.save(info);
        notifyTransactionMessageFacade.deleteNotifyTransactionMessageByUUIDAndQueue(info);//测试消息通知结果处理完成但是删除消息失败
    }
}
