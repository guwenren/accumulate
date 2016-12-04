package com.guwr.accumulate.common.dao;

import com.guwr.accumulate.common.page.PageParam;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.common.dao.BaseRepository
 * Date         2016/11/24
 * Time         9:26
 * Description
 */
public class BaseRepository<T> {

    private static Logger logger = LoggerFactory.getLogger(BaseRepository.class);

    protected EntityManager em;

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public Integer listPageCount(String listPageCountStr) {
        System.out.println("BaseRepository.listPageCount.listPageCountStr = [" + listPageCountStr + "]");
        Query query = em.createNativeQuery(listPageCountStr);
        BigInteger result = (BigInteger) query.getSingleResult();
        return result.intValue();
    }

    public List<T> listPage(String listPageStr, PageParam pageParam) {
        logger.info("BaseRepository.listPage.listPageStr = [" + listPageStr + "], pageParam = [" + pageParam + "]");
        Query query = em.createNativeQuery(listPageStr);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(getClazz()));
        query.setFirstResult(pageParam.getFirstResult());
        query.setMaxResults(pageParam.getNumPerPage());
        return query.getResultList();
    }

    private Class<T> getClazz() {
        Class<T> entityClass = null;
        Type t = getClass().getGenericSuperclass();
        if (t instanceof ParameterizedType) {
            Type[] p = ((ParameterizedType) t).getActualTypeArguments();
            entityClass = (Class<T>) p[0];
        }
        return entityClass;
    }
}
