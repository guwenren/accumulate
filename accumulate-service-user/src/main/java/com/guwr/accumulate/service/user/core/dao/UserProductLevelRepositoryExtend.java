package com.guwr.accumulate.service.user.core.dao;

import com.guwr.accumulate.facade.user.entity.UserProductLevel;
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
 * Path com.accumulate.service.user.core.dao.UserProductLevelRepositoryExtend
 * Date 2016/8/13
 * Time 21:19
 */
public class UserProductLevelRepositoryExtend {

    private static Logger logger = LoggerFactory.getLogger(UserProductLevelRepositoryExtend.class);
    @PersistenceContext
    private EntityManager em;

    public UserProductLevel findOneByInvest(BigDecimal invest) {
        String qlString = "from  UserProductLevel o where ? >=o.minInvest and ? < o.maxInvest";
        Query query = em.createQuery(qlString);
        query.setParameter(1, invest);
        query.setParameter(2, invest);
        UserProductLevel result = null;
        try {
            result = (UserProductLevel) query.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        }
        return result;
    }
}
