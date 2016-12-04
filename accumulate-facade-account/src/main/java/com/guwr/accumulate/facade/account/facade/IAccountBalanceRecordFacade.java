package com.guwr.accumulate.facade.account.facade;

import com.guwr.accumulate.facade.account.entity.AccountBalanceRecord;
import com.guwr.accumulate.facade.account.vo.AccountBalanceRecordVO;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.notify.facade.IAccountBalanceFacade
 * Date 2016/8/21
 * Time 18:33
 */
public interface IAccountBalanceRecordFacade {
    AccountBalanceRecord outgo(AccountBalanceRecordVO vo);

    AccountBalanceRecord income(AccountBalanceRecordVO vo);
}
