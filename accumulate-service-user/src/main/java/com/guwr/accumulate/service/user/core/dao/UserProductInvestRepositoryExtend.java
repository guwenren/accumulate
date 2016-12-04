package com.guwr.accumulate.service.user.core.dao;

import com.guwr.accumulate.facade.user.entity.UserProductInvest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by gwr
 * Description
 * Path com.accumulate.service.user.core.dao.UserProductInvestRepositoryExtend
 * Date 2016/8/13
 * Time 21:19
 */
public class UserProductInvestRepositoryExtend {


    private static Logger logger = LoggerFactory.getLogger(UserProductInvestRepositoryExtend.class);
    @PersistenceContext
    private EntityManager em;

    public UserProductInvest findOneByUid(Integer uid) {
        String qlString = "from  UserProductInvest o where o.uid = ?";
        Query query = em.createQuery(qlString);
        query.setParameter(1, uid);
        UserProductInvest result = null;
        try {
            result = (UserProductInvest) query.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        }
        return result;
    }
}
