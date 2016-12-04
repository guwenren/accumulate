package com.guwr.accumulate.service.job.core.service;


import com.guwr.accumulate.facade.job.entity.QrtzTriggerInfo;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.wmps.service.IQrtzTriggerInfoService
 * Date 2016/8/13
 * Time 21:07
 */
public interface IQrtzTriggerInfoService {

    QrtzTriggerInfo save(QrtzTriggerInfo entity);

    QrtzTriggerInfo findOneByGroupAndName(String jobGroup, String jobName);
}
