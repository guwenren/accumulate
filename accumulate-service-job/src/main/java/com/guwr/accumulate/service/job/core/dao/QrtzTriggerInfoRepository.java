package com.guwr.accumulate.service.job.core.dao;

import com.guwr.accumulate.facade.job.entity.QrtzTriggerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by gwr
 * Description
 * Path com.accumulate.service.wmps.core.dao.QrtzTriggerInfoRepository
 * Date 2016/8/13
 * Time 21:19
 */
public interface QrtzTriggerInfoRepository extends JpaRepository<QrtzTriggerInfo, Integer> {
    List<QrtzTriggerInfo> findByGroupAndName(String jobGroup, String jobName);
}
