package com.guwr.accumulate.service.account.core.dao;

import com.guwr.accumulate.facade.account.entity.AccountBalanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by gwr
 * Description
 * Path com.accumulate.service.account.core.dao.AccountBalanceRepository.java
 * Date 2016/8/13
 * Time 21:19
 */
public interface AccountBalanceRecordRepository extends JpaRepository<AccountBalanceRecord, Integer> {

}
