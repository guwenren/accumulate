package com.guwr.accumulate.service.account.core.service;


import com.guwr.accumulate.facade.account.entity.AccountBalance;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.account.service.IAccountBalanceService
 * Date 2016/8/13
 * Time 21:07
 */
public interface IAccountBalanceService {
    AccountBalance save(AccountBalance entity);

    AccountBalance findOne(Integer id);

    AccountBalance findOneByUid(Integer uid);
}
