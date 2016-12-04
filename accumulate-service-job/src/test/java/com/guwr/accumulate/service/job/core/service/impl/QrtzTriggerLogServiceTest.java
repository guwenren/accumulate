package com.guwr.accumulate.service.job.core.service.impl;

import com.guwr.accumulate.facade.job.entity.QrtzTriggerLog;
import com.guwr.accumulate.service.job.BaseTest;
import com.guwr.accumulate.service.job.core.service.IQrtzTriggerLogService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.job.core.service.impl.QrtzTriggerLogServiceTest
 * Date 2016/9/20
 * Time 13:21
 */
public class QrtzTriggerLogServiceTest extends BaseTest {

    @Autowired
    private IQrtzTriggerLogService service;

    @Test
    public void save() throws Exception {
        QrtzTriggerLog qrtzTriggerLog = new QrtzTriggerLog();
        qrtzTriggerLog.setJobName("defaultJobName");
        qrtzTriggerLog.setJobGroup("defaultJobGroup");
        service.save(qrtzTriggerLog);
        System.out.println("save OK");
    }
}