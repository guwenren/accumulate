package com.guwr.accumulate.service.notify.core.service.impl;

import com.guwr.accumulate.facade.notify.entity.NotifyMessage;
import com.guwr.accumulate.service.notify.BaseTest;
import com.guwr.accumulate.service.notify.core.service.INotifyMessageService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.notify.core.service.impl.NotifyMessageServiceTest
 * Date 2016/9/12
 * Time 15:20
 */
public class NotifyMessageServiceTest extends BaseTest {

    @Autowired
    private INotifyMessageService notifyMessageService;

    @Test
    public void save() throws Exception {
        long l = System.currentTimeMillis();
        NotifyMessage notifyMessage = new NotifyMessage();
        notifyMessage.setUid(1);
        notifyMessage.setTitle("title" + l);
        notifyMessage.setContent("content" + l);
        notifyMessageService.save(notifyMessage);
    }

    @Test
    public void findOne() throws Exception {
        NotifyMessage notifyMessage = notifyMessageService.findOne(1);
        System.out.println("notifyMessage = " + notifyMessage);
    }
}