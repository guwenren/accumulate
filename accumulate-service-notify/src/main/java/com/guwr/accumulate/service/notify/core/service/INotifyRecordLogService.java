package com.guwr.accumulate.service.notify.core.service;


import com.guwr.accumulate.facade.notify.entity.NotifyRecordLog;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.notify.service.INotifyRecordLogService
 * Date 2016/8/13
 * Time 21:07
 */
public interface INotifyRecordLogService {
    /**
     * @param entity
     * @return
     */
    NotifyRecordLog save(NotifyRecordLog entity);


    NotifyRecordLog update(NotifyRecordLog entity);

    /**
     * @param id
     * @return
     */
    NotifyRecordLog findOne(Integer id);
}
