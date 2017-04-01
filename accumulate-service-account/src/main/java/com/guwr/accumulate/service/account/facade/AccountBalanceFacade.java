package com.guwr.accumulate.service.account.facade;

import com.guwr.accumulate.facade.account.entity.AccountBalance;
import com.guwr.accumulate.facade.account.exception.AccountException;
import com.guwr.accumulate.facade.account.facade.IAccountBalanceFacade;
import com.guwr.accumulate.facade.account.vo.AccountBalanceVO;
import com.guwr.accumulate.service.account.core.service.IAccountBalanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.account.facade.NotifyRecordFacade
 * Date 2016/8/21
 * Time 18:31
 */
@Component
public class AccountBalanceFacade implements IAccountBalanceFacade {

    private static Logger logger = LoggerFactory.getLogger(AccountBalanceFacade.class);
    @Autowired
    private IAccountBalanceService accountBalanceService;

    @Override
    public AccountBalance saveAccountBalance(AccountBalanceVO info) {
        logger.info("AccountBalanceFacade.saveAccountBalance.info = [" + info + "]");
        Integer uid = info.getUid();
        BigDecimal balance = BigDecimal.ZERO;
        AccountBalance accountBalance = new AccountBalance();
        accountBalance.setBalance(balance);
        accountBalance.setUid(uid);
        accountBalance.setUuid(info.getUuid());
        return accountBalanceService.save(accountBalance);
    }

    @Override
    public AccountBalance updateAccountBalance(AccountBalanceVO info) {
        return null;
    }

    @Override
    public AccountBalance findOneByUid(int uid) {
        logger.info("AccountBalanceFacade.findOneByUid.uid = [" + uid + "]");
        AccountBalance accountBalance = accountBalanceService.findOneByUid(uid);
        return accountBalance;
    }

    @Override
    public AccountBalance findOneByUidCheck(int uid) {
        logger.info("AccountBalanceFacade.findOneByUidCheck.uid = [" + uid + "]");
        AccountBalance accountBalance = this.findOneByUid(uid);
        if (accountBalance == null) { //账户
            throw AccountException.YONG_HU_ZHANG_HU_BU_CUN_ZAI.print();
        }
        return accountBalance;
    }
}
