package com.guwr.accumulate.facade.notify.facade;

import com.guwr.accumulate.common.page.PageBean;
import com.guwr.accumulate.common.web.vo.BaseVO;
import com.guwr.accumulate.facade.notify.entity.NotifyMessage;
import com.guwr.accumulate.facade.notify.vo.NotifyMessageVO;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.notify.facade.INotifyMessageFacade
 * Date 2016/8/21
 * Time 18:33
 */
public interface INotifyMessageFacade {
    PageBean<NotifyMessage> findListPageByCondition(NotifyMessageVO info);

    NotifyMessage saveNotifyMessage(NotifyMessageVO info);

    NotifyMessage saveNotifyMessageAndFlush(NotifyMessageVO info);

    NotifyMessage findNotifyMessageByUUID(String uuid);

    void sendNotifyMessage(Integer id);

    void sendNotifyMessage(NotifyMessage entity);

    NotifyMessage saveAndSendNotifyMessage(NotifyMessageVO info);

    void deleteNotifyMessageById(Integer id);

    void deleteNotifyMessageByUUID(String uuid);

    void deleteNotifyMessageByUUIDAndQueue(String uuid, String consumerQueue);

    void deleteNotifyMessageByUUIDAndQueue(BaseVO info);

    NotifyMessage update(NotifyMessage entity);

    void repeatSendNotifyMessage(NotifyMessage entity);
}
