package com.guwr.accumulate.queue.notify.message;

import com.alibaba.fastjson.JSON;
import com.guwr.accumulate.facade.notify.facade.INotifyTransactionMessageFacade;
import com.guwr.accumulate.facade.wmps.facade.IProductRecordFacade;
import com.guwr.accumulate.facade.wmps.vo.ProductRecordVO;
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
 * Path         com.guwr.accumulate.queue.notify.message.ProductRecordMessageListener
 * Date         2016/11/25
 * Time         11:38
 * Description  用户投资记录利率逾期收益变更
 */
@Component
public class ProductRecordMessageListener implements SessionAwareMessageListener<Message> {

    private static Logger logger = LoggerFactory.getLogger(ProductRecordMessageListener.class);

    @Autowired
    private INotifyTransactionMessageFacade notifyTransactionMessageFacade;

    @Autowired
    private IProductRecordFacade productRecordFacade;

    @Override
    public void onMessage(Message message, Session session) throws JMSException {
        logger.info("onMessage.message = [" + message + "], session = [" + session + "]");
        ActiveMQTextMessage textMessage = (ActiveMQTextMessage) message;
        String msgText = textMessage.getText();
        logger.info("msgText = " + msgText);
        ProductRecordVO info = JSON.parseObject(msgText, ProductRecordVO.class);
        productRecordFacade.updateProearnAndInterestrateByUUID(info);
        String uuid = info.getUuid();
        String consumerQueue = info.getConsumerQueue();
        notifyTransactionMessageFacade.deleteNotifyTransactionMessageByUUIDAndQueue(uuid,consumerQueue);
    }
}
