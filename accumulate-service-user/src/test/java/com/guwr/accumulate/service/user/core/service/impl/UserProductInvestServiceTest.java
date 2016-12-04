package com.guwr.accumulate.service.user.core.service.impl;

import com.guwr.accumulate.common.util.CommonUtils;
import com.guwr.accumulate.facade.user.entity.UserProductInvest;
import com.guwr.accumulate.facade.user.entity.UserProductLevel;
import com.guwr.accumulate.facade.user.vo.UserProductInvestVO;
import com.guwr.accumulate.service.user.BaseTest;
import com.guwr.accumulate.service.user.core.service.IUserProductInvestService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.service.user.core.service.impl.UserProductInvestServiceTest
 * Date         2016/11/19
 * Time         17:06
 * Description
 */
public class UserProductInvestServiceTest extends BaseTest {
    @Test
    public void changeInInvest() throws Exception {
        UserProductInvestVO info = new UserProductInvestVO();
        info.setUid(10);
        info.setInvest(new BigDecimal(30009.88));
        userProductInvestService.changeInInvest(info);
//        System.out.println("userProductLevel = " + userProductLevel);
    }

    @Test
    public void changeOutInvest() throws Exception {

    }

    @Autowired
    private IUserProductInvestService userProductInvestService;

    @Test
    public void save() throws Exception {
        UserProductInvest userProductInvest = new UserProductInvest();
        Date date = new Date();

        userProductInvest.setCreateTime(date);
        userProductInvest.setUpdateTime(date);
        userProductInvest.setTotalInvest(BigDecimal.ONE);
        userProductInvest.setUid(6);
        UserProductInvest productInvest = userProductInvestService.save(userProductInvest);

        CommonUtils.printObj2Json(productInvest);
    }

    @Test
    public void findOne() throws Exception {
        UserProductInvest userProductInvest = userProductInvestService.findOne(1);
        CommonUtils.printObj2Json(userProductInvest);
    }
}