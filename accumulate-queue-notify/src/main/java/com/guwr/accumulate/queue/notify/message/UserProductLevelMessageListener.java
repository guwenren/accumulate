package com.guwr.accumulate.queue.notify.message;

import com.alibaba.fastjson.JSON;
import com.guwr.accumulate.facade.notify.facade.INotifyMessageFacade;
import com.guwr.accumulate.facade.user.facade.IUserProductLevelFacade;
import com.guwr.accumulate.facade.user.vo.UserProductLevelVO;
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
 * Path         com.guwr.accumulate.queue.notify.message.UserProductLevelMessageListener
 * Date         2016/11/25
 * Time         11:38
 * Description  用户投资级别变换
 */
@Component
public class UserProductLevelMessageListener implements SessionAwareMessageListener<Message> {

    private static Logger logger = LoggerFactory.getLogger(UserProductLevelMessageListener.class);

    @Autowired
    private INotifyMessageFacade notifyMessageFacade;

    @Autowired
    private IUserProductLevelFacade userProductLevelFacade;

    @Override
    public void onMessage(Message message, Session session) throws JMSException {
        logger.info("message = [" + message + "], session = [" + session + "]");
        ActiveMQTextMessage textMessage = (ActiveMQTextMessage) message;
        String msgText = textMessage.getText();
        logger.info("msgText = " + msgText);
        UserProductLevelVO info = JSON.parseObject(msgText, UserProductLevelVO.class);
        userProductLevelFacade.findUserProductLevelByIn(info);
        notifyMessageFacade.deleteNotifyMessageByUUIDAndQueue(info);
    }
}
