package com.guwr.accumulate.facade.notify.facade;

import com.guwr.accumulate.facade.notify.entity.NotifyRecordLog;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.notify.facade.INotifyRecordLogFacade
 * Date 2016/8/21
 * Time 18:33
 */
public interface INotifyRecordLogFacade {
    NotifyRecordLog save(NotifyRecordLog entity);

    NotifyRecordLog findOne(Integer id);

    NotifyRecordLog update(NotifyRecordLog entity);
}
