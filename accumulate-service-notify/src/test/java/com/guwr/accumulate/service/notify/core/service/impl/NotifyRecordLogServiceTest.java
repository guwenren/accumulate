package com.guwr.accumulate.service.notify.core.service.impl;

import com.guwr.accumulate.common.util.StringUtils;
import com.guwr.accumulate.facade.notify.entity.NotifyRecordLog;
import com.guwr.accumulate.service.notify.BaseTest;
import com.guwr.accumulate.service.notify.core.service.INotifyRecordLogService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.service.notify.core.service.impl.NotifyRecordLogServiceTest
 * Date         2016/11/6
 * Time         11:55
 * Description
 */
public class NotifyRecordLogServiceTest extends BaseTest {
    @Autowired
    private INotifyRecordLogService notifyRecordLogService;

    @Test
    public void save() throws Exception {
        Date date = new Date();
        NotifyRecordLog entity = new NotifyRecordLog();
        entity.setCreateTime(date);
        entity.setMerchantNo("1");
        entity.setMerchantOrderNo("111");
        entity.setHttpStatus(101);
        entity.setNotifyId(1);
        entity.setRequest("request");
        entity.setResponse("response");
        notifyRecordLogService.save(entity);
    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void findOne() throws Exception {
        NotifyRecordLog notifyRecordLog = notifyRecordLogService.findOne(1);
        System.out.println("notifyRecordLog = " + notifyRecordLog);
    }
}