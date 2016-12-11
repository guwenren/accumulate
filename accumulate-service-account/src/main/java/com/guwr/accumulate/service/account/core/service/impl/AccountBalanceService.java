package com.guwr.accumulate.service.account.core.service.impl;


import com.guwr.accumulate.facade.account.entity.AccountBalance;
import com.guwr.accumulate.facade.account.exception.AccountBizException;
import com.guwr.accumulate.service.account.core.dao.AccountBalanceRepository;
import com.guwr.accumulate.service.account.core.service.IAccountBalanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.account.service.impl.ProductService
 * Date 2016/8/13
 * Time 21:16
 */
@Service
public class AccountBalanceService implements IAccountBalanceService {

    private static Logger logger = LoggerFactory.getLogger(AccountBalanceService.class);
    @Autowired
    private AccountBalanceRepository repository;


    @Override
    public AccountBalance save(AccountBalance entity) {
        return repository.save(entity);
    }

    @Override
    public AccountBalance findOne(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public AccountBalance findOneByUid(Integer uid) {
        if (uid == null) {
            logger.info("uid = " + uid);
        }
        List<AccountBalance> accountBalances = repository.findOneByUid(uid);
        if (CollectionUtils.isEmpty(accountBalances)) {
            throw AccountBizException.YONG_HU_ZHANG_HU_BU_CUN_ZAI.print();
        }
        return accountBalances.get(0);
    }
}
