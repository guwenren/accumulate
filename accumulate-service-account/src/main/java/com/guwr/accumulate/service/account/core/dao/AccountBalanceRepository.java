package com.guwr.accumulate.service.account.core.dao;

import com.guwr.accumulate.facade.account.entity.AccountBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by gwr
 * Description
 * Path com.accumulate.service.account.core.dao.AccountBalanceRepository
 * Date 2016/8/13
 * Time 21:19
 */
public interface AccountBalanceRepository extends JpaRepository<AccountBalance, Integer> {
    /**
     * 
     * @param uid
     * @return
     */
    List<AccountBalance> findOneByUid(int uid);
}
