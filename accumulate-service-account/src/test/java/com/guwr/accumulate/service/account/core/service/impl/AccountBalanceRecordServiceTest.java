package com.guwr.accumulate.service.account.core.service.impl;

import com.guwr.accumulate.common.util.StringUtils;
import com.guwr.accumulate.facade.account.entity.AccountBalanceRecord;
import com.guwr.accumulate.facade.account.enums.AccountBalanceRecordEnum;
import com.guwr.accumulate.service.account.BaseTest;
import com.guwr.accumulate.service.account.core.service.IAccountBalanceRecordService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.account.core.service.impl.AccountBalanceRecordServiceTest
 * Date 2016/9/12
 * Time 13:41
 */
public class AccountBalanceRecordServiceTest extends BaseTest {


    @Autowired
    private IAccountBalanceRecordService accountBalanceRecordService;

    @Test
    public void save() throws Exception {
        AccountBalanceRecord accountBalanceRecord = new AccountBalanceRecord();
        accountBalanceRecord.setUid(1);
        accountBalanceRecord.setType(AccountBalanceRecordEnum.AccountBalanceRecordEnumType.INCOME.getValue());
        accountBalanceRecord.setBalance(new BigDecimal(777));
        accountBalanceRecord.setAmount(new BigDecimal(55));
        AccountBalanceRecord save = accountBalanceRecordService.save(accountBalanceRecord);
        System.out.println("save = " + save);
    }

    @Test
    public void findOne() throws Exception {
        AccountBalanceRecord accountBalanceRecord = accountBalanceRecordService.findOne(1);
        System.out.println("accountBalanceRecord = " + accountBalanceRecord);
    }

    @Test
    public void outgo() throws Exception {
        AccountBalanceRecord accountBalanceRecord = new AccountBalanceRecord();
        accountBalanceRecord.setUid(1);
        accountBalanceRecord.setAmount(new BigDecimal(98.5));
        accountBalanceRecord.setDescription("添加支出流水");
        AccountBalanceRecord save = accountBalanceRecordService.outgo(accountBalanceRecord);
        System.out.println("save = " + save);
    }

    @Test
    public void income() throws Exception {
        for (int i = 0; i < 10; i++) {
            String uuid = StringUtils.getUUID();
            AccountBalanceRecord accountBalanceRecord = new AccountBalanceRecord();
            accountBalanceRecord.setUid(i + 3);
            accountBalanceRecord.setAmount(new BigDecimal(100000));
            accountBalanceRecord.setDescription("添加收入流水");
            accountBalanceRecord.setUuid(uuid);
            AccountBalanceRecord save = accountBalanceRecordService.income(accountBalanceRecord);
            System.out.println("save = " + save);
        }
    }
}