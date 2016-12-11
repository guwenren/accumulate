package com.guwr.accumulate.service.user.core.service.impl;

import com.guwr.accumulate.common.util.StringUtils;
import com.guwr.accumulate.facade.user.entity.UserProductLevel;
import com.guwr.accumulate.service.user.BaseTest;
import com.guwr.accumulate.service.user.core.service.IUserProductLevelService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.service.user.core.service.impl.UserProductLevelServiceTest
 * Date         2016/11/21
 * Time         15:07
 * Description
 */
public class UserProductLevelServiceTest extends BaseTest {
    @Test
    public void save() throws Exception {
        Date date = new Date();
        String uuid = StringUtils.getUUID();
        UserProductLevel userProductLevel = new UserProductLevel();
        userProductLevel.setCreateTime(date);
        userProductLevel.setUpdateTime(date);
        userProductLevel.setLevel(5);
        userProductLevel.setMinInvest(new BigDecimal(500000d));
        userProductLevel.setMaxInvest(new BigDecimal(600000d));
        userProductLevel.setInterestrate(new BigDecimal(0.0050));
        userProductLevel.setUuid(uuid);
        userProductLevelService.save(userProductLevel);
    }

    @Test
    public void findOneByInvest() throws Exception {
        UserProductLevel userProductLevel = userProductLevelService.findOneByInvest(new BigDecimal(100000));
        System.out.println("userProductLevel = " + userProductLevel);
    }

    @Autowired
    private IUserProductLevelService userProductLevelService;
}