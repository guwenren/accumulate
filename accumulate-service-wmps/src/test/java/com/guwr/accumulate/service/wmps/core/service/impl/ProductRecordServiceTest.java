package com.guwr.accumulate.service.wmps.core.service.impl;

import com.guwr.accumulate.facade.wmps.entity.ProductRecord;
import com.guwr.accumulate.facade.wmps.vo.ProductRecordVO;
import com.guwr.accumulate.service.wmps.BaseTest;
import com.guwr.accumulate.service.wmps.core.service.IProductRecordService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.wmps.core.service.impl.ProductRecordServiceTest
 * Date 2016/9/8
 * Time 16:40
 */
public class ProductRecordServiceTest extends BaseTest {


    @Autowired
    private IProductRecordService productRecordService;

    @Test
    public void save() throws Exception {
        Date date = new Date();
        ProductRecord productRecord = new ProductRecord();
        productRecord.setInvestAmount(new BigDecimal(50));
        productRecord.setEffectAmount(new BigDecimal(50));
        productRecord.setCreateTime(date);
        productRecord.setUpdateTime(date);
        productRecord.setPid(1);
        productRecord.setUid(1);
        productRecordService.save(productRecord);
    }

    @Test
    public void findOne() throws Exception {

    }

    @Test
    public void addProductRecord() throws Exception {
//        for (int i = 0; i < 10; i++) {
            ProductRecordVO vo = new ProductRecordVO();
            vo.setUid(4);
            vo.setPid(1);
            vo.setInvestAmount(new BigDecimal(100));
            productRecordService.addProductRecord(vo);
//        }
    }
}