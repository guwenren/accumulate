package com.guwr.accumulate.service.notify.core.service.impl;

import com.guwr.accumulate.common.page.PageBean;
import com.guwr.accumulate.common.util.StringUtils;
import com.guwr.accumulate.facade.notify.entity.NotifyTransactionMessage;
import com.guwr.accumulate.facade.notify.vo.NotifyTransactionMessageVO;
import com.guwr.accumulate.service.notify.BaseTest;
import com.guwr.accumulate.service.notify.core.service.INotifyTransactionMessageService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.service.notify.core.service.impl.NotifyTransactionMessageServiceTest
 * Date         2016/11/23
 * Time         20:25
 * Description
 */
public class NotifyTransactionMessageServiceTest extends BaseTest {
    @Test
    public void findListPage() throws Exception {
        NotifyTransactionMessageVO info = new NotifyTransactionMessageVO();
        PageBean<NotifyTransactionMessage> listPage = notifyTransactionMessageService.findListPageByCondition(info);
        System.out.println("listPage = " + listPage);
    }

    @Autowired
    private INotifyTransactionMessageService notifyTransactionMessageService;

    @Test
    public void saveNotifyTransactionMessage() throws Exception {
        NotifyTransactionMessage notifyTransactionMessage = new NotifyTransactionMessage();
        Date date = new Date();
        String uuid = StringUtils.getUUID();
        notifyTransactionMessage.setCreateTime(date);
        notifyTransactionMessage.setUpdateTime(date);
        notifyTransactionMessage.setMessageBody("messageBody");
        notifyTransactionMessage.setMessageDataType("messageDataType");
        notifyTransactionMessage.setMessageSendTimes(0);
        notifyTransactionMessage.setAreadlyDead(1);
        notifyTransactionMessage.setConsumerQueue("consumerQueue");
        notifyTransactionMessage.setRemark("remark");
        notifyTransactionMessage.setStatus(1);
        notifyTransactionMessage.setUuid(uuid);
        notifyTransactionMessageService.saveNotifyTransactionMessage(notifyTransactionMessage);
    }

    @Test
    public void sendNotifyTransactionMessage() throws Exception {

    }
}