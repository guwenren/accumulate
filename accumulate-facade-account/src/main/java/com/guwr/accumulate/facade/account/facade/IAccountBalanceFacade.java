package com.guwr.accumulate.facade.account.facade;

import com.guwr.accumulate.facade.account.entity.AccountBalance;
import com.guwr.accumulate.facade.account.vo.AccountBalanceVO;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.notify.facade.IAccountBalanceFacade
 * Date 2016/8/21
 * Time 18:33
 */
public interface IAccountBalanceFacade {
    AccountBalance save(AccountBalanceVO vo);

    AccountBalance update(AccountBalanceVO vo);

    AccountBalance findOneByUid(int uid);

    AccountBalance findOneByUidCheck(int uid);
}
