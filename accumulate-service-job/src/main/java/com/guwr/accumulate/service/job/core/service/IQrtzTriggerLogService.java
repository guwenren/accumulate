package com.guwr.accumulate.service.job.core.service;


import com.guwr.accumulate.facade.job.entity.QrtzTriggerLog;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.wmps.service.IQrtzTriggerInfoService
 * Date 2016/8/13
 * Time 21:07
 */
public interface IQrtzTriggerLogService {

    QrtzTriggerLog save(QrtzTriggerLog entity);

    QrtzTriggerLog update(QrtzTriggerLog entity);

    int updateTriggerInfo(QrtzTriggerLog entity);

    int updateHandleInfo(QrtzTriggerLog entity);

    QrtzTriggerLog findOne(int id);

}
