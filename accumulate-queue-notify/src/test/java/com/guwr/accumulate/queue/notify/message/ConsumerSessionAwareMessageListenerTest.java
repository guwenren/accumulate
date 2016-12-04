package com.guwr.accumulate.queue.notify.message;

import com.alibaba.fastjson.JSON;
import com.guwr.accumulate.facade.notify.entity.NotifyRecord;
import com.guwr.accumulate.facade.notify.enums.NotifyRecordEnum;
import com.guwr.accumulate.queue.notify.BaseTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.queue.notify.message.ConsumerSessionAwareMessageListenerTest
 * Date 2016/8/21
 * Time 13:12
 */
public class ConsumerSessionAwareMessageListenerTest extends BaseTest {
    private static Logger LOGGER = LoggerFactory.getLogger(ConsumerSessionAwareMessageListenerTest.class);
    @Autowired
    private JmsTemplate jmsTemplate;

    @Test
    public void onMessage() throws Exception {
         NotifyRecord param = new NotifyRecord();
         param.setNotifyTimes(0);
         param.setLimitNotifyTimes(5);
         param.setStatus(101);
         param.setUrl("http://www.eloancn.com");
         param.setNotifyType(NotifyRecordEnum.NotifyRecordEnumType.MERCHANT.getValue());
        final String str = JSON.toJSONString(param);
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(str);
            }
        });
    }
}