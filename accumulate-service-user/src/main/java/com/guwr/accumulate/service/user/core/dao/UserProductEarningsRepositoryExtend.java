package com.guwr.accumulate.service.user.core.dao;

import com.guwr.accumulate.facade.user.entity.UserProductEarnings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;

/**
 * Created by gwr
 * Description
 * Path com.accumulate.service.wmps.core.dao.UserProductEarningsRepositoryExtend
 * Date 2016/8/13
 * Time 21:19
 */
public class UserProductEarningsRepositoryExtend {

    private static Logger logger = LoggerFactory.getLogger(UserProductEarningsRepositoryExtend.class);
    @PersistenceContext
    private EntityManager em;

    public UserProductEarnings findOneByUidPidInterestrate(Integer uid, Integer pid, BigDecimal interestrate) {
        String qlString = "from  UserProductEarnings o where o.uid = ?  and o.pid = ? and o.interestrate = ? ";
        Query query = em.createQuery(qlString);
        query.setParameter(1, uid);
        query.setParameter(2, pid);
        query.setParameter(3, interestrate);
        UserProductEarnings result = null;
        try {
            result = (UserProductEarnings) query.getSingleResult();
        } catch (NoResultException e) {
//            e.printStackTrace();
            logger.info(e.getMessage());
        }
        return result;
    }
}
