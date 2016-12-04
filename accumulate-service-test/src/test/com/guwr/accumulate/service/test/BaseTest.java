package com.guwr.accumulate.service.test;

import com.alibaba.fastjson.JSON;
import com.eloancn.nback.finance.common.result.ResultFinance;
import com.eloancn.nback.financeapi.AccountPaymentAPI;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.wmps.BaseTest
 * Date 2016/8/20
 * Time 19:03
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-context.xml")
public class BaseTest {

//    @Autowired
//    private AccountPaymentAPI accountPaymentAPI;

    @Test
    public void test1() {
//        String noticeNumber = "NOTICE_CUR_PRODUCT_ASSIGNMENT_OF_CREDIT";
//        Object[] titleParams=new Object[]{};
//        Object[] contentParams=new Object[] {  "2016","4","15","60000","123456789","张三","430481999999999","123","李四","48119880909"};
//        Integer uid  = 802;
//        ResultFinance resultFinance = accountPaymentAPI.sendInstationMessage(noticeNumber, titleParams, contentParams, uid);
//
//        System.out.println("resultFinance = " + JSON.toJSONString(resultFinance));
    }
}