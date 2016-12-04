package com.guwr.accumulate.service.job.core.dao;

import com.guwr.accumulate.facade.job.entity.QrtzTriggerLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by gwr
 * Description
 * Path com.accumulate.service.wmps.core.dao.QrtzTriggerInfoRepository
 * Date 2016/8/13
 * Time 21:19
 */
public interface QrtzTriggerLogRepository extends JpaRepository<QrtzTriggerLog, Integer> {

    /**
     * 异步调用回退通知
     * @param entity
     * @return
     */
    int updateHandleInfo(QrtzTriggerLog entity);

    /**
     * 请求成功后修改日志信息
     * @param entity
     * @return
     */
    int updateTriggerInfo(QrtzTriggerLog entity);
}
