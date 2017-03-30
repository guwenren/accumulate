package com.guwr.accumulate.service.notify.core.service;


import com.guwr.accumulate.common.page.PageBean;
import com.guwr.accumulate.facade.notify.entity.NotifyMessage;
import com.guwr.accumulate.facade.notify.vo.NotifyMessageVO;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.notify.service.INotifyMessageService
 * Date 2016/8/13
 * Time 21:07
 */
public interface INotifyMessageService {
    /**
     * 保存
     *
     * @param entity
     * @return
     */
    NotifyMessage saveNotifyMessage(NotifyMessage entity);

    /**
     *
     * @param entity
     * @return
     */
    NotifyMessage saveNotifyMessageAndFlush(NotifyMessage entity);

    /**
     * 发送
     *
     * @param id
     * @return
     */
    void sendNotifyMessage(Integer id);

    /**
     *
     * @param entity
     */
    void sendNotifyMessage(NotifyMessage entity);

    /**
     * 保存并发送
     *
     * @param entity
     * @return
     */
    NotifyMessage saveAndSendNotifyMessage(NotifyMessage entity);

    /**
     * 直接发送消息
     *
     * @param entity
     */
    void directSendNotifyMessage(NotifyMessage entity);

    /**
     * 重新发送消息
     *
     * @param entity
     */
    void repeatSendNotifyMessage(NotifyMessage entity);

    /**
     * 根据ID重新发送消息
     *
     * @param id
     */
    void repeatSendNotifyMessageById(Integer id);

    /**
     * 批量处理指定队列中已死亡的消息
     *
     * @param destinationName
     * @param batchSize
     */
    void repeatSendNotifyMessageByDestinationName(String destinationName, Integer batchSize);

    /**
     * 删除消息
     *
     * @param id
     */
    void deleteNotifyMessageById(Integer id);


    /**
     *
     * @param info
     * @return
     */
    PageBean<NotifyMessage> findListPageByCondition(NotifyMessageVO info);


    /**
     *
     * @param uuid
     * @return
     */
    int deleteNotifyMessageByUUID(String uuid);

    /**
     *
     * @param entity
     * @return
     */
    NotifyMessage update(NotifyMessage entity);

    /**
     * 删除消息根据UUID和队列名称
     *
     * @param uuid
     * @param consumerQueue
     */
    int deleteNotifyMessageByUUIDAndQueue(String uuid, String consumerQueue);
}
