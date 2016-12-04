package com.guwr.accumulate.service.wmps.core.dao;

import com.guwr.accumulate.facade.wmps.entity.ProductRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by gwr
 * Description
 * Path com.accumulate.service.wmps.core.dao.ProductRecordRepositoryExtend
 * Date 2016/8/13
 * Time 21:19
 */
public class ProductRecordRepositoryExtend {

    private static Logger logger = LoggerFactory.getLogger(ProductRecordRepositoryExtend.class);
    @PersistenceContext
    protected EntityManager em;

    /**
     * @param uuid
     * @return
     */
    public ProductRecord findOneProductRecordByUUID(String uuid) {
        String qlString = "from  ProductRecord o where o.uuid = ?";
        Query query = em.createQuery(qlString);
        query.setParameter(1, uuid);
        ProductRecord result = null;
        try {
            result = (ProductRecord) query.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        }
        return result;
    }
}
