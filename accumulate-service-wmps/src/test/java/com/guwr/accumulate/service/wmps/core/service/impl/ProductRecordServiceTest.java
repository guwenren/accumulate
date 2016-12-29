package com.guwr.accumulate.service.wmps.core.service.impl;

import com.guwr.accumulate.common.util.DateUtils;
import com.guwr.accumulate.facade.wmps.entity.ProductRecord;
import com.guwr.accumulate.facade.wmps.entity.ProductRecordExtend;
import com.guwr.accumulate.facade.wmps.vo.ProductRecordVO;
import com.guwr.accumulate.service.wmps.BaseTest;
import com.guwr.accumulate.service.wmps.core.service.IProductRecordService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
    public void interestTask() throws Exception {
        productRecordService.interestTask();
        System.in.read();
    }

    @Test
    public void findInterestCount() throws Exception {
        int queryDate = DateUtils.currentTimeSeconds();
        int interestCount = productRecordService.findInterestCount(queryDate);
        System.out.println("interestCount = " + interestCount);
    }

    @Test
    public void findProductRecordExtendListByMOD() throws Exception {
        List<ProductRecordExtend> productRecordExtendListByMOD = productRecordService.findProductRecordExtendListByMOD(4, 0, 1481817600);
        System.out.println("productRecordExtendListByMOD = " + productRecordExtendListByMOD);
    }


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
//        for (int i = 9; i >= 9; i--) {
//            ProductRecordVO vo = new ProductRecordVO();
//            vo.setUid(i - 2);
//            vo.setPid(i - 1);
//            vo.setInvestAmount(new BigDecimal(i * 1300));
//            productRecordService.addProductRecord(vo);
//        }
//        System.in.read();
        for (int b = 0; b < 10; b++) {
            java.util.Random random = new java.util.Random();// 定义随机类
            int i = random.nextInt(10);// 返回[0,10)集合中的整数，注意不包括10
            if (i == 0) {
                continue;
            }
            ProductRecordVO vo = new ProductRecordVO();
            vo.setUid(1);
            vo.setPid(1);
            vo.setInvestAmount(new BigDecimal(i * 3000));
            productRecordService.addProductRecord(vo);
        }
        System.in.read();
    }
}