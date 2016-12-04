package com.guwr.accumulate.facade.notify.facade;

import com.guwr.accumulate.common.page.PageBean;
import com.guwr.accumulate.facade.notify.entity.NotifyRecord;
import com.guwr.accumulate.facade.notify.vo.NotifyRecordVO;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.notify.facade.INotifyRecordFacade
 * Date 2016/8/21
 * Time 18:33
 */
public interface INotifyRecordFacade {
    NotifyRecord save(NotifyRecord entity);

    NotifyRecord findOne(Integer id);

    NotifyRecord update(NotifyRecord entity);

    PageBean<NotifyRecord> findListPage(NotifyRecordVO info);
}
