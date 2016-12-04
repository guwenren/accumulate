package com.guwr.accumulate.service.account.core.dao;

import com.guwr.accumulate.facade.account.entity.AccountBalance;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by gwr
 * Description
 * Path com.accumulate.service.account.core.dao.AccountBalanceRepositoryExtend
 * Date 2016/8/13
 * Time 21:19
 */
public class AccountBalanceRepositoryExtend {
    @PersistenceContext
    private EntityManager entityManager;


    public List<AccountBalance> findOneByUid(int uid) {
        String qlString = " from AccountBalance o where o.uid = ?";
        Query query = entityManager.createQuery(qlString);
        query.setParameter(1, uid);
        return query.getResultList();
    }
}
