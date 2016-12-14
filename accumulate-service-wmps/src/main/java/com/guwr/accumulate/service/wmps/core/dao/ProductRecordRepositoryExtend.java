package com.guwr.accumulate.service.wmps.core.dao;

import com.guwr.accumulate.facade.wmps.entity.ProductRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

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

    public int findInterestCount(int interestDate) {
        StringBuilder qlStringB = new StringBuilder();
        qlStringB.append(" SELECT count(DISTINCT(a.uid)) FROM tbl_wmps_product_record a");
        qlStringB.append("  LEFT JOIN tbl_wmps_product b on a.pid=b.id");
        qlStringB.append(" WHERE a.status=7 AND b.status=3");
        qlStringB.append("  and  b.interdate >= ? and b.enddate > ?");
        Query query = em.createNativeQuery(qlStringB.toString());
        query.setParameter(1, interestDate);
        query.setParameter(2, interestDate);
        BigInteger result = (BigInteger) query.getSingleResult();
        return result.intValue();
    }

    public List<Integer> findListByMOD(Integer number, Integer interestDate) {
        StringBuilder qlStringB = new StringBuilder();
        qlStringB.append(" SELECT count(DISTINCT(a.uid)) FROM tbl_wmps_product_record a");
        qlStringB.append("  LEFT JOIN tbl_wmps_product b on a.pid=b.id");
        qlStringB.append(" WHERE a.status=7 AND b.status=3");
        qlStringB.append("  and  b.interdate >= ? and b.enddate > ?");
        return null;
    }
}
