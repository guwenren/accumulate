package com.guwr.accumulate.service.account.core.service;


import com.guwr.accumulate.facade.account.entity.AccountBalanceRecord;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.account.service.IAccountBalanceService
 * Date 2016/8/13
 * Time 21:07
 */
public interface IAccountBalanceRecordService {
    AccountBalanceRecord save(AccountBalanceRecord entity);

    AccountBalanceRecord outgo(AccountBalanceRecord entity);

    AccountBalanceRecord income(AccountBalanceRecord entity);

    AccountBalanceRecord findOne(Integer id);
}
