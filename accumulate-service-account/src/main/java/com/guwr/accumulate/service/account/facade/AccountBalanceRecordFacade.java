package com.guwr.accumulate.service.account.facade;

import com.guwr.accumulate.facade.account.entity.AccountBalanceRecord;
import com.guwr.accumulate.facade.account.enums.AccountBalanceRecordEnum;
import com.guwr.accumulate.facade.account.facade.IAccountBalanceRecordFacade;
import com.guwr.accumulate.facade.account.vo.AccountBalanceRecordVO;
import com.guwr.accumulate.service.account.core.service.IAccountBalanceRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.account.facade.NotifyRecordFacade
 * Date 2016/8/21
 * Time 18:31
 */
@Component
public class AccountBalanceRecordFacade implements IAccountBalanceRecordFacade {
    private static Logger logger = LoggerFactory.getLogger(AccountBalanceRecordFacade.class);
    @Autowired
    private IAccountBalanceRecordService accountBalanceRecordService;

    @Override
    public AccountBalanceRecord outgo(AccountBalanceRecordVO info) {
        logger.info("AccountBalanceRecordFacade.outgo");
        AccountBalanceRecord accountBalanceRecord = new AccountBalanceRecord();
        accountBalanceRecord.setBalance(info.getBalance());
        accountBalanceRecord.setUid(info.getUid());
        accountBalanceRecord.setType(AccountBalanceRecordEnum.AccountBalanceRecordEnumType.OUTGO.getValue());
        accountBalanceRecord.setAmount(info.getAmount());
        accountBalanceRecord.setUuid(info.getUuid());
        return accountBalanceRecordService.outgo(accountBalanceRecord);
    }

    @Override
    public AccountBalanceRecord income(AccountBalanceRecordVO info) {
        logger.info("AccountBalanceRecordFacade.income");//
        AccountBalanceRecord accountBalanceRecord = new AccountBalanceRecord();
        accountBalanceRecord.setBalance(info.getBalance());
        accountBalanceRecord.setUid(info.getUid());
        accountBalanceRecord.setType(AccountBalanceRecordEnum.AccountBalanceRecordEnumType.INCOME.getValue());
        accountBalanceRecord.setAmount(info.getAmount());
        accountBalanceRecord.setUuid(info.getUuid());
        return accountBalanceRecordService.income(accountBalanceRecord);
    }

    @Override
    public void  income(List<AccountBalanceRecordVO> vos) {
        logger.info("AccountBalanceRecordFacade.income");//
        List<AccountBalanceRecord> accountBalanceRecords = new ArrayList<>();
        for (AccountBalanceRecordVO vo : vos) {
            AccountBalanceRecord accountBalanceRecord = new AccountBalanceRecord();
            accountBalanceRecord.setBalance(vo.getBalance());
            accountBalanceRecord.setUid(vo.getUid());
            accountBalanceRecord.setType(AccountBalanceRecordEnum.AccountBalanceRecordEnumType.INCOME.getValue());
            accountBalanceRecord.setAmount(vo.getAmount());
            accountBalanceRecord.setUuid(vo.getUuid());
            accountBalanceRecords.add(accountBalanceRecord);
        }
        accountBalanceRecordService.saveAccountBalanceRecords(accountBalanceRecords);
    }
}
