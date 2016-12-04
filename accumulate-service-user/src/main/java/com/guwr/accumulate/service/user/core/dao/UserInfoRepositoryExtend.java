package com.guwr.accumulate.service.user.core.dao;

import com.guwr.accumulate.common.dao.BaseRepository;
import com.guwr.accumulate.common.page.PageParam;
import com.guwr.accumulate.facade.user.entity.UserInfo;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by gwr
 * Description
 * Path com.accumulate.service.user.core.dao.UserInfoRepositoryExtend
 * Date 2016/8/13
 * Time 21:19
 */
public class UserInfoRepositoryExtend extends BaseRepository<UserInfo> {
    private static Logger logger = LoggerFactory.getLogger(UserInfoRepositoryExtend.class);

    @PersistenceContext
    @Override
    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<UserInfo> findOneByMobile(String mobile) {
        logger.info("UserInfoRepositoryExtend.findOneByMobile");
        String qlString = "from  UserInfo o where o.mobile = ?";
        Query query = em.createQuery(qlString);
        query.setParameter(1, mobile);
        return query.getResultList();
    }

//    public Integer listPageCount(String listPageCount) {
//        logger.info("UserInfoRepositoryExtend.listPageCount");
//        Query query = em.createNativeQuery(listPageCount);
//        BigInteger result = (BigInteger) query.getSingleResult();
//        return result.intValue();
//    }
//
//    public List<UserInfo> listPage(String listPage, PageParam pageParam) {
//        logger.info("UserInfoRepositoryExtend.listPage");
//        Query query = em.createNativeQuery(listPage);
//        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(UserInfo.class));
//        query.setFirstResult(pageParam.getFirstResult());
//        query.setMaxResults(pageParam.getNumPerPage());
//        return query.getResultList();
//    }
}
