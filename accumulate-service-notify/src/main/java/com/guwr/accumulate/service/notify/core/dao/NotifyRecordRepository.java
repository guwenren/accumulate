package com.guwr.accumulate.service.notify.core.dao;

import com.guwr.accumulate.common.page.PageParam;
import com.guwr.accumulate.facade.notify.entity.NotifyRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by gwr
 * Description
 * Path com.accumulate.service.notify.core.dao.NotifyRecordRepository
 * Date 2016/8/13
 * Time 21:19
 */
public interface NotifyRecordRepository extends JpaRepository<NotifyRecord, Integer> {

    Integer listPageCount(String listPageCount);

    List<NotifyRecord> listPage(String listPage, PageParam pageParam);
}
