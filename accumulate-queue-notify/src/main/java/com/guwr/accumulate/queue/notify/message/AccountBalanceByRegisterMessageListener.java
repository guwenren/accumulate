package com.guwr.accumulate.queue.notify.message;

import com.alibaba.fastjson.JSON;
import com.guwr.accumulate.facade.account.facade.IAccountBalanceFacade;
import com.guwr.accumulate.facade.account.vo.AccountBalanceVO;
import com.guwr.accumulate.facade.notify.facade.INotifyTransactionMessageFacade;
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
 * Path         com.guwr.accumulate.queue.notify.message.AccountBalanceByRegisterMessageListener
 * Date         2016/11/25
 * Time         11:38
 * Description  用户注册账户添加
 */
@Component
public class AccountBalanceByRegisterMessageListener implements SessionAwareMessageListener<Message> {

    private static Logger logger = LoggerFactory.getLogger(AccountBalanceByRegisterMessageListener.class);

    @Autowired
    private IAccountBalanceFacade accountBalanceFacade;

    @Autowired
    private INotifyTransactionMessageFacade notifyTransactionMessageFacade;

    @Override
    public void onMessage(Message message, Session session) throws JMSException {
        logger.info("AccountBalanceByRegisterMessageListener.onMessage.message = [" + message + "], session = [" + session + "]");
        ActiveMQTextMessage textMessage = (ActiveMQTextMessage) message;
        String msgText = textMessage.getText();
        logger.info("msgText = " + msgText);
        AccountBalanceVO info = JSON.parseObject(msgText, AccountBalanceVO.class);
        accountBalanceFacade.saveAccountBalance(info);
        notifyTransactionMessageFacade.deleteNotifyTransactionMessageByUUIDAndQueue(info);
    }
}
