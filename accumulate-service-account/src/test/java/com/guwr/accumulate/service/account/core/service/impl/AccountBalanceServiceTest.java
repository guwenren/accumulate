package com.guwr.accumulate.service.account.core.service.impl;

import com.guwr.accumulate.facade.account.entity.AccountBalance;
import com.guwr.accumulate.service.account.BaseTest;
import com.guwr.accumulate.service.account.core.service.IAccountBalanceService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.account.core.service.impl.AccountBalanceServiceTest
 * Date 2016/9/12
 * Time 10:49
 */
public class AccountBalanceServiceTest extends BaseTest {

    @Autowired
    private IAccountBalanceService accountBalanceService;

    @Test
    public void save() throws Exception {
        AccountBalance userInfo = new AccountBalance();
        userInfo.setBalance(new BigDecimal(777.77));
        userInfo.setUid(1);
        accountBalanceService.save(userInfo);
    }

    @Test
    public void findOne() throws Exception {
        AccountBalance accountBalance = accountBalanceService.findOne(1);
        System.out.println("accountBalance = " + accountBalance);
    }

}