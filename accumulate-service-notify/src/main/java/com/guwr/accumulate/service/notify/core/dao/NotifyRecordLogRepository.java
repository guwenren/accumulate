package com.guwr.accumulate.service.notify.core.dao;

import com.guwr.accumulate.facade.notify.entity.NotifyRecordLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by gwr
 * Description
 * Path com.accumulate.service.notify.core.dao.NotifyRecordLogRepository
 * Date 2016/8/13
 * Time 21:19
 */
public interface NotifyRecordLogRepository extends JpaRepository<NotifyRecordLog, Integer> {

}
