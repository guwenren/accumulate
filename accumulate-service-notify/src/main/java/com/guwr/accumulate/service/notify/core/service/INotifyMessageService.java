package com.guwr.accumulate.service.notify.core.service;


import com.guwr.accumulate.common.page.PageBean;
import com.guwr.accumulate.facade.notify.entity.NotifyMessage;
import com.guwr.accumulate.facade.notify.entity.NotifyRecord;

import java.util.List;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.notify.service.INotifyMessageService
 * Date 2016/8/13
 * Time 21:07
 */
public interface INotifyMessageService {
    Integer countNotifyMessage();

    NotifyMessage getNotifyMessage(Integer id);

    List<NotifyMessage> listNotifyMessage();

    PageBean<NotifyMessage> pageNotifyMessage();

    void removeNotifyMessage(Integer id);

    NotifyMessage updateNotifyMessage(NotifyMessage entity);

    /**
     *
     * @param entity
     * @return
     */
    NotifyMessage save(NotifyMessage entity);

    NotifyMessage findOne(Integer id);
}
