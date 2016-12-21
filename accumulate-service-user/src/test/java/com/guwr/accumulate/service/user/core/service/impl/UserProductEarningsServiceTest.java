package com.guwr.accumulate.service.user.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.guwr.accumulate.facade.user.entity.UserProductEarnings;
import com.guwr.accumulate.facade.user.facade.IUserProductEarningsFacade;
import com.guwr.accumulate.service.user.BaseTest;
import com.guwr.accumulate.service.user.core.service.IUserProductEarningsService;
import com.guwr.accumulate.service.user.core.service.IUserProductInvestService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.service.user.core.service.impl.UserProductEarningsServiceTest
 * Date         2016/12/21
 * Time         11:23
 * Description
 */
public class UserProductEarningsServiceTest extends BaseTest {

    @Autowired
    private IUserProductEarningsService userProductEarningsService;
    @Autowired
    private IUserProductEarningsFacade userProductEarningsFacade;
    @Test
    public void save() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void findOneByUidPidInterestrate() throws Exception {
        UserProductEarnings userProductEarnings = userProductEarningsFacade.findOneByUidPidInterestrate(7, 5, new BigDecimal(0.00));

        System.out.println("JSON.toJSONString(userProductEarnings) = " + JSON.toJSONString(userProductEarnings));
    }
}