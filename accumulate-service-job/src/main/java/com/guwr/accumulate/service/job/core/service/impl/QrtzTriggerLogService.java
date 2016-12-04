package com.guwr.accumulate.service.job.core.service.impl;


import com.guwr.accumulate.facade.job.entity.QrtzTriggerLog;
import com.guwr.accumulate.service.job.core.dao.QrtzTriggerLogRepository;
import com.guwr.accumulate.service.job.core.service.IQrtzTriggerLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.wmps.service.impl.ProductRecordService
 * Date 2016/8/13
 * Time 21:16
 */
@Service
public class QrtzTriggerLogService implements IQrtzTriggerLogService {

    private static Logger logger = LoggerFactory.getLogger(QrtzTriggerLogService.class);

    @Autowired
    private QrtzTriggerLogRepository repository;

    @Override
    public QrtzTriggerLog save(QrtzTriggerLog entity) {
        logger.info("QrtzTriggerLogService.save");
        return repository.save(entity);
    }

    @Override
    public QrtzTriggerLog update(QrtzTriggerLog entity) {
        logger.info("QrtzTriggerLogService.update");
        return repository.save(entity);
    }

    @Transactional
    @Override
    public int updateTriggerInfo(QrtzTriggerLog entity) {
        logger.info("QrtzTriggerLogService.updateTriggerInfo");
        return repository.updateTriggerInfo(entity);
    }

    @Transactional
    @Override
    public int updateHandleInfo(QrtzTriggerLog entity) {
        logger.info("QrtzTriggerLogService.updateHandleInfo");
        return repository.updateHandleInfo(entity);
    }

    @Override
    public QrtzTriggerLog findOne(int id) {
        logger.info("QrtzTriggerLogService.findOne");
        return repository.findOne(id);
    }
}
