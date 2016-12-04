package com.guwr.accumulate.service.notify.core.dao;

import com.guwr.accumulate.common.dao.BaseRepository;
import com.guwr.accumulate.facade.notify.entity.NotifyTransactionMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.Modifying;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by gwr
 * Description
 * Path com.accumulate.service.notify.core.dao.NotifyTransactionMessageRepositoryExtend
 * Date 2016/8/13
 * Time 21:19
 */
public class NotifyTransactionMessageRepositoryExtend extends BaseRepository<NotifyTransactionMessage> {

    private static Logger logger = LoggerFactory.getLogger(NotifyTransactionMessageRepositoryExtend.class);

    @PersistenceContext
    @Override
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Modifying
    public int deleteNotifyTransactionMessageByUUID(String uuid) {
        logger.info("NotifyTransactionMessageRepositoryExtend.deleteNotifyTransactionMessageByUUID.uuid = " + uuid);
        String qlString = " delete NotifyTransactionMessage o where o.uuid = ?";
        Query query = em.createQuery(qlString);
        query.setParameter(1, uuid);
        int executeUpdate = query.executeUpdate();
        return executeUpdate;
    }
}
