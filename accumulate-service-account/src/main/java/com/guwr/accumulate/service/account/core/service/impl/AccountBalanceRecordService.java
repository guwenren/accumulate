package com.guwr.accumulate.service.account.core.service.impl;


import com.guwr.accumulate.facade.account.entity.AccountBalance;
import com.guwr.accumulate.facade.account.entity.AccountBalanceRecord;
import com.guwr.accumulate.facade.account.enums.AccountBalanceRecordEnum;
import com.guwr.accumulate.facade.account.exception.AccountBizException;
import com.guwr.accumulate.service.account.core.dao.AccountBalanceRecordRepository;
import com.guwr.accumulate.service.account.core.service.IAccountBalanceRecordService;
import com.guwr.accumulate.service.account.core.service.IAccountBalanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.account.service.impl.ProductService
 * Date 2016/8/13
 * Time 21:16
 */
@Service
public class AccountBalanceRecordService implements IAccountBalanceRecordService {


    private static Logger logger = LoggerFactory.getLogger(AccountBalanceRecordService.class);
    @Autowired
    private AccountBalanceRecordRepository repository;

    @Autowired
    private IAccountBalanceService accountBalanceService;

    @Override
    public AccountBalanceRecord save(AccountBalanceRecord entity) {
        return repository.save(entity);
    }

    @Override
    public AccountBalanceRecord outgo(AccountBalanceRecord entity) {
        logger.info("AccountBalanceRecordService.outgo");
        BigDecimal amount = entity.getAmount(); //支出金额
        Integer uid = entity.getUid();   //用户id

        AccountBalance accountBalance = accountBalanceService.findOneByUid(uid);
        //账户余额
        BigDecimal balance = accountBalance.getBalance();
        //余额不足
        if (amount.compareTo(balance) > 0) {
            AccountBizException.YU_E_BU_ZU.print();
        }
        BigDecimal subtractBalance = balance.subtract(amount);
        accountBalance.setBalance(subtractBalance);
        accountBalanceService.save(accountBalance);

        entity.setBalance(subtractBalance);
        entity.setType(AccountBalanceRecordEnum.AccountBalanceRecordEnumType.OUTGO.getValue());
        return repository.save(entity);
    }

    @Override
    public AccountBalanceRecord income(AccountBalanceRecord entity) {
        logger.info("AccountBalanceRecordService.income");
        Date date = new Date();
        BigDecimal amount = entity.getAmount(); //收入金额
        Integer uid = entity.getUid();   //用户id
        AccountBalance accountBalance = accountBalanceService.findOneByUid(uid);
        //账户余额
        BigDecimal balance = accountBalance.getBalance();
        BigDecimal addBalance = balance.add(amount);
        accountBalance.setBalance(addBalance);
        accountBalance.setUpdateTime(date);
        accountBalanceService.save(accountBalance);

        entity.setBalance(addBalance);
        entity.setType(AccountBalanceRecordEnum.AccountBalanceRecordEnumType.INCOME.getValue());
        return repository.save(entity);
    }

    @Override
    public AccountBalanceRecord findOne(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public void saveAccountBalanceRecords(List<AccountBalanceRecord> accountBalanceRecords) {
        logger.info("AccountBalanceRecordService.saveAccountBalanceRecords");
        for (AccountBalanceRecord accountBalanceRecord : accountBalanceRecords) {
            income(accountBalanceRecord);
        }
    }
}
