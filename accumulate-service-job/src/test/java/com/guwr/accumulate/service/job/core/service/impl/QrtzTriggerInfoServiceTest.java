package com.guwr.accumulate.service.job.core.service.impl;

import com.guwr.accumulate.facade.job.entity.QrtzTriggerInfo;
import com.guwr.accumulate.service.job.BaseTest;
import com.guwr.accumulate.service.job.core.service.IQrtzTriggerInfoService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.job.core.service.impl.QrtzTriggerInfoServiceTest
 * Date 2016/9/20
 * Time 11:29
 */
public class QrtzTriggerInfoServiceTest extends BaseTest {


    @Autowired
    private IQrtzTriggerInfoService service;

    @Test
    public void save() throws Exception {
        QrtzTriggerInfo entity = new QrtzTriggerInfo();
        entity.setJobGroup("defaults");
        entity.setJobName("201609141528030875");
        entity.setJobCron("0 0/30 * * * ? *");
        entity.setJobDesc("描述");
        entity.setAddTime(new Date());
        entity.setUpdateTime(new Date());
        entity.setAuthor("zhangsan");
        entity.setAlarmEmail("395994939@qq.com");
        entity.setExecutorAddress("127.0.0.1:9999");
        entity.setExecutorHandler("demoJobHandler");
        entity.setExecutorParam("test");
        entity.setGlueSwitch(0);
//        entity.setGlueSource("");
        entity.setGlueRemark("");
//        entity.setChildJobkey();
        System.out.println("service = " + service);

        service.save(entity);
    }

    @Test
    public void findOneByGroupAndName() throws Exception {
        QrtzTriggerInfo qrtzTriggerInfo = service.findOneByGroupAndName("defaults", "201609141528030875");
        System.out.println("qrtzTriggerInfo = " + qrtzTriggerInfo);
    }
}