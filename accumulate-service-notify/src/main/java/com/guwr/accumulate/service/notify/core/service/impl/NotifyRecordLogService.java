package com.guwr.accumulate.service.notify.core.service.impl;


import com.guwr.accumulate.facade.notify.entity.NotifyRecordLog;
import com.guwr.accumulate.service.notify.core.dao.NotifyRecordLogRepository;
import com.guwr.accumulate.service.notify.core.service.INotifyRecordLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.notify.service.impl.NotifyRecordLogService
 * Date 2016/8/13
 * Time 21:16
 */
@Service
public class NotifyRecordLogService implements INotifyRecordLogService {

    private static Logger logger = LoggerFactory.getLogger(NotifyRecordLogService.class);
    @Autowired
    private NotifyRecordLogRepository repository;

    @Override
    public NotifyRecordLog save(NotifyRecordLog entity) {
        logger.info("NotifyRecordLogService.saveNotifyMessage");
        return repository.save(entity);
    }


    @Override
    public NotifyRecordLog update(NotifyRecordLog entity) {
        logger.info("NotifyRecordLogService.updateNotifyRecord");
        return repository.save(entity);
    }

    @Override
    public NotifyRecordLog findOne(Integer id) {
        return repository.findOne(id);
    }
}
