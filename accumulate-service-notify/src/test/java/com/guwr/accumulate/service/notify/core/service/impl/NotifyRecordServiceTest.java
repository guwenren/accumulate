package com.guwr.accumulate.service.notify.core.service.impl;

import com.guwr.accumulate.common.page.PageBean;
import com.guwr.accumulate.common.util.StringUtils;
import com.guwr.accumulate.facade.notify.entity.NotifyRecord;
import com.guwr.accumulate.facade.notify.vo.NotifyRecordVO;
import com.guwr.accumulate.service.notify.BaseTest;
import com.guwr.accumulate.service.notify.core.service.INotifyRecordService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.notify.core.service.impl.NotifyRecordServiceTest
 * Date 2016/9/10
 * Time 15:15
 */
public class NotifyRecordServiceTest extends BaseTest {


    @Autowired
    private INotifyRecordService notifyRecordService;

    @Test
    public void findListPage() throws Exception {
        List<Integer> status = Arrays.asList(101, 102, 200, 201);
        NotifyRecordVO info = new NotifyRecordVO();
        info.setPageNum(1);
        info.setNumPerPage(50);
        info.setStatus(status);
        PageBean listPage = notifyRecordService.findListPage(info);
        System.out.println("listPage = " + listPage);
    }

    @Test
    public void testSave() throws Exception {
        for (int i = 0; i < 123; i++) {
            Date date = new Date();
            String uuid = StringUtils.getUUID();
            NotifyRecord entity = new NotifyRecord();
            entity.setCreateTime(date);
            entity.setUpdateTime(date);
            entity.setLimitNotifyTimes(5);
            entity.setNotifyTimes(0);
            entity.setNotifyType(1);
            entity.setStatus(0);
            entity.setUrl("www.baidu.com");
            entity.setMerchantNo("1");
            entity.setMerchantOrderNo("111");
            entity.setUuid(uuid);
            notifyRecordService.save(entity);
        }
    }

    @Test
    public void testUpdate() throws Exception {
        NotifyRecord notifyRecord = notifyRecordService.findOne(1);
        notifyRecord.setMerchantNo("2");
        notifyRecord.setMerchantOrderNo("22");
        notifyRecordService.update(notifyRecord);
    }


    @Test
    public void testFindOne() throws Exception {
        NotifyRecord notifyRecord = notifyRecordService.findOne(1);
        System.out.println("notifyRecord = " + notifyRecord);
    }

    @Test
    public void sendMessage() throws Exception {
        notifyRecordService.sendMessage(1);
    }

}