package com.guwr.accumulate.service.job.core.dao;

import com.guwr.accumulate.facade.job.entity.QrtzTriggerLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by gwr
 * Description
 * Path com.accumulate.service.wmps.core.dao.QrtzTriggerInfoRepository
 * Date 2016/8/13
 * Time 21:19
 */
public class QrtzTriggerLogRepositoryExtend {
    private static Logger logger = LoggerFactory.getLogger(QrtzTriggerLogRepositoryExtend.class);
    @PersistenceContext
    private EntityManager entityManager;


    public int updateHandleInfo(QrtzTriggerLog entity) {
        logger.info("QrtzTriggerLogRepositoryExtend.updateHandleInfo");
        String qlString = " update QrtzTriggerLog o set o.handleTime = ?,o.handleStatus = ?,o.handleMsg = ?  where o.id = ?";
        Query query = entityManager.createQuery(qlString);
        query.setParameter(1, entity.getHandleTime());
        query.setParameter(2, entity.getHandleStatus());
        query.setParameter(3, entity.getHandleMsg());
        query.setParameter(4, entity.getId());
        return query.executeUpdate();
    }

    public int updateTriggerInfo(QrtzTriggerLog entity) {
        logger.info("QrtzTriggerLogRepositoryExtend.updateTriggerInfo");
        String qlString = " update QrtzTriggerLog o set o.triggerTime = ?,o.triggerStatus = ?,o.triggerMsg = ? ,o.executorAddress = ? ,o.executorHandler = ? ,o.executorParam = ?  where o.id = ?";
        Query query = entityManager.createQuery(qlString);
        query.setParameter(1, entity.getTriggerTime());
        query.setParameter(2, entity.getTriggerStatus());
        query.setParameter(3, entity.getTriggerMsg());
        query.setParameter(4, entity.getExecutorAddress());
        query.setParameter(5, entity.getExecutorHandler());
        query.setParameter(6, entity.getExecutorParam());
        query.setParameter(7, entity.getId());
        return query.executeUpdate();
    }
}
