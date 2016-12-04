package com.guwr.accumulate.service.notify.core.service;


import com.guwr.accumulate.common.page.PageBean;
import com.guwr.accumulate.facade.notify.entity.NotifyRecord;
import com.guwr.accumulate.facade.notify.vo.NotifyRecordVO;
import org.springframework.data.domain.Page;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.notify.service.INotifyRecordService
 * Date 2016/8/13
 * Time 21:07
 */
public interface INotifyRecordService {
    /**
     *
     * @param entity
     * @return
     */
    NotifyRecord save(NotifyRecord entity);

    PageBean<NotifyRecord> findListPage(NotifyRecordVO info);

    /**
     *
     * @param nid
     */
    void sendMessage(int nid);

    NotifyRecord update(NotifyRecord entity);

    /**
     *
     * @param id
     * @return
     */
    NotifyRecord findOne(Integer id);
}
