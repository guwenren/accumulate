package com.guwr.accumulate.service.job.core.dao;

import com.guwr.accumulate.facade.job.entity.QrtzTriggerInfo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by gwr
 * Description
 * Path com.accumulate.service.wmps.core.dao.QrtzTriggerInfoRepository
 * Date 2016/8/13
 * Time 21:19
 */
public class QrtzTriggerInfoRepositoryExtend {
    @PersistenceContext
    private EntityManager entityManager;


    public List<QrtzTriggerInfo> findByGroupAndName(String jobGroup, String jobName) {
        List<QrtzTriggerInfo> qrtzTriggerInfos = null;
        String qlString = " from QrtzTriggerInfo o where o.jobGroup = ? and o.jobName = ?";
        Query query = entityManager.createQuery(qlString);
        query.setParameter(1, jobGroup);
        query.setParameter(2, jobName);
        qrtzTriggerInfos = query.getResultList();
        return qrtzTriggerInfos;
    }
}
