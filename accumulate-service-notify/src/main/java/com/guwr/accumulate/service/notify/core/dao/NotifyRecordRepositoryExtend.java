package com.guwr.accumulate.service.notify.core.dao;

import com.guwr.accumulate.common.page.PageParam;
import com.guwr.accumulate.facade.notify.entity.NotifyRecord;
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
 * Path com.accumulate.service.notify.core.dao.NotifyRecordRepositoryExtend
 * Date 2016/8/13
 * Time 21:19
 */
public class NotifyRecordRepositoryExtend implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(NotifyRecordRepositoryExtend.class);
    @PersistenceContext
    private EntityManager em;

    public Integer listPageCount(String listPageCount) {
        logger.info("listPageCount = [" + listPageCount + "]");
        Query query = em.createNativeQuery(listPageCount);
        BigInteger result = (BigInteger) query.getSingleResult();
        return result.intValue();
    }

    public List<NotifyRecord> listPage(String listPage, PageParam pageParam) {
        logger.info("listPage = [" + listPage + "], pageParam = [" + pageParam + "]");
        Query query = em.createNativeQuery(listPage);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(NotifyRecord.class));
        query.setFirstResult(pageParam.getFirstResult());
        query.setMaxResults(pageParam.getNumPerPage());
        return query.getResultList();
    }
}
