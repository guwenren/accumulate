package com.guwr.accumulate.service.notify.core.service.impl;

import com.guwr.accumulate.common.page.PageBean;
import com.guwr.accumulate.common.util.StringUtils;
import com.guwr.accumulate.facade.notify.entity.NotifyMessage;
import com.guwr.accumulate.facade.notify.vo.NotifyMessageVO;
import com.guwr.accumulate.service.notify.BaseTest;
import com.guwr.accumulate.service.notify.core.service.INotifyMessageService;
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
public class NotifyMessageServiceTest extends BaseTest {
    @Test
    public void findListPage() throws Exception {
        NotifyMessageVO info = new NotifyMessageVO();
        PageBean<NotifyMessage> listPage = notifyMessageService.findListPageByCondition(info);
        System.out.println("listPage = " + listPage);
    }

    @Autowired
    private INotifyMessageService notifyMessageService;

    @Test
    public void saveNotifyTransactionMessage() throws Exception {
        NotifyMessage notifyTransactionMessage = new NotifyMessage();
        Date date = new Date();
        String uuid = StringUtils.getUUID();
        notifyTransactionMessage.setCreateTime(date);
        notifyTransactionMessage.setUpdateTime(date);
        notifyTransactionMessage.setMessageBody("messageBody");
        notifyTransactionMessage.setMessageSendTimes(0);
        notifyTransactionMessage.setAreadlyDead(1);
        notifyTransactionMessage.setConsumerQueue("consumerQueue");
        notifyTransactionMessage.setRemark("remark");
        notifyTransactionMessage.setStatus(1);
        notifyTransactionMessage.setUuid(uuid);
        notifyMessageService.saveNotifyMessage(notifyTransactionMessage);
    }

    @Test
    public void sendNotifyTransactionMessage() throws Exception {

    }
}