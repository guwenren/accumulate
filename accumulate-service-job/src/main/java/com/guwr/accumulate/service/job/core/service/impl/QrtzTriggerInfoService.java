package com.guwr.accumulate.service.job.core.service.impl;


import com.guwr.accumulate.facade.job.entity.QrtzTriggerInfo;
import com.guwr.accumulate.service.job.core.dao.QrtzTriggerInfoRepository;
import com.guwr.accumulate.service.job.core.service.IQrtzTriggerInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.wmps.service.impl.ProductRecordService
 * Date 2016/8/13
 * Time 21:16
 */
@Service
public class QrtzTriggerInfoService implements IQrtzTriggerInfoService {

    private static Logger logger = LoggerFactory.getLogger(QrtzTriggerInfoService.class);

    @Autowired
    private QrtzTriggerInfoRepository repository;

    @Override
    public QrtzTriggerInfo save(QrtzTriggerInfo entity) {
        return repository.save(entity);
    }

    @Override
    public QrtzTriggerInfo findOneByGroupAndName(String jobGroup, String jobName) {
        List<QrtzTriggerInfo> qrtzTriggerInfos = repository.findByGroupAndName(jobGroup, jobName);
        if (CollectionUtils.isEmpty(qrtzTriggerInfos)) {
            throw new RuntimeException("查询结果为空");
        }
        return qrtzTriggerInfos.get(0);
    }
}
