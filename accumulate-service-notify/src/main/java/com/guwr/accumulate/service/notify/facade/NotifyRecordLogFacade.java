package com.guwr.accumulate.service.notify.facade;

import com.guwr.accumulate.facade.notify.entity.NotifyRecordLog;
import com.guwr.accumulate.facade.notify.facade.INotifyRecordLogFacade;
import com.guwr.accumulate.service.notify.core.service.INotifyRecordLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.notify.facade.NotifyRecordLogFacade
 * Date 2016/8/21
 * Time 18:31
 */
@Component
public class NotifyRecordLogFacade implements INotifyRecordLogFacade {
    private static Logger logger = LoggerFactory.getLogger(NotifyRecordLogFacade.class);
    @Autowired
    private INotifyRecordLogService service;

    @Override
    public NotifyRecordLog save(NotifyRecordLog entity) {
        logger.info("NotifyRecordLogFacade.saveNotifyMessage");
        return service.save(entity);
    }

    @Override
    public NotifyRecordLog findOne(Integer id) {
        logger.info("NotifyRecordLogFacade.findOne");
        return service.findOne(id);
    }

    @Override
    public NotifyRecordLog update(NotifyRecordLog entity) {
        logger.info("NotifyRecordLogFacade.updateNotifyRecord");
        return service.update(entity);
    }
}
