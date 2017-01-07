package com.guwr.accumulate.facade.notify.facade;

import com.guwr.accumulate.common.page.PageBean;
import com.guwr.accumulate.common.web.vo.BaseVO;
import com.guwr.accumulate.facade.notify.entity.NotifyTransactionMessage;
import com.guwr.accumulate.facade.notify.vo.NotifyTransactionMessageVO;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.notify.facade.INotifyTransactionMessageFacade
 * Date 2016/8/21
 * Time 18:33
 */
public interface INotifyTransactionMessageFacade {
    PageBean<NotifyTransactionMessage> findListPageByCondition(NotifyTransactionMessageVO info);

    NotifyTransactionMessage saveNotifyTransactionMessage(NotifyTransactionMessageVO info);

    NotifyTransactionMessage saveNotifyTransactionMessageAndFlush(NotifyTransactionMessageVO info);

    NotifyTransactionMessage findNotifyTransactionMessageByUUID(String uuid);

    void sendNotifyTransactionMessage(Integer id);

    void sendNotifyTransactionMessage(NotifyTransactionMessage entity);

    NotifyTransactionMessage saveAndSendNotifyTransactionMessage(NotifyTransactionMessageVO info);

    void deleteNotifyTransactionMessageById(Integer id);

    void deleteNotifyTransactionMessageByUUID(String uuid);

    void deleteNotifyTransactionMessageByUUIDAndQueue(String uuid, String consumerQueue);

    void deleteNotifyTransactionMessageByUUIDAndQueue(BaseVO info);

    NotifyTransactionMessage update(NotifyTransactionMessage entity);

    void repeatSendNotifyTransactionMessage(NotifyTransactionMessage entity);
}
