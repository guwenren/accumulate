package com.guwr.accumulate.service.notify.core.service;


import com.guwr.accumulate.common.page.PageBean;
import com.guwr.accumulate.facade.notify.entity.NotifyTransactionMessage;
import com.guwr.accumulate.facade.notify.vo.NotifyTransactionMessageVO;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.notify.service.INotifyTransactionMessageService
 * Date 2016/8/13
 * Time 21:07
 */
public interface INotifyTransactionMessageService {
    /**
     * 保存
     *
     * @param entity
     * @return
     */
    NotifyTransactionMessage saveNotifyTransactionMessage(NotifyTransactionMessage entity);

    /**
     *
     * @param entity
     * @return
     */
    NotifyTransactionMessage saveNotifyTransactionMessageAndFlush(NotifyTransactionMessage entity);

    /**
     * 发送
     *
     * @param id
     * @return
     */
    void sendNotifyTransactionMessage(Integer id);

    /**
     *
     * @param entity
     */
    void sendNotifyTransactionMessage(NotifyTransactionMessage entity);

    /**
     * 保存并发送
     *
     * @param entity
     * @return
     */
    NotifyTransactionMessage saveAndSendNotifyTransactionMessage(NotifyTransactionMessage entity);

    /**
     * 直接发送消息
     *
     * @param entity
     */
    void directSendNotifyTransactionMessage(NotifyTransactionMessage entity);

    /**
     * 重新发送消息
     *
     * @param entity
     */
    void repeatSendNotifyTransactionMessage(NotifyTransactionMessage entity);

    /**
     * 根据ID重新发送消息
     *
     * @param id
     */
    void repeatSendNotifyTransactionMessageById(Integer id);

    /**
     * 批量处理指定队列中已死亡的消息
     *
     * @param destinationName
     * @param batchSize
     */
    void repeatSendNotifyTransactionMessageByDestinationName(String destinationName, Integer batchSize);

    /**
     * 删除消息
     *
     * @param id
     */
    void deleteNotifyTransactionMessageById(Integer id);


    /**
     *
     * @param info
     * @return
     */
    PageBean<NotifyTransactionMessage> findListPageByCondition(NotifyTransactionMessageVO info);


    /**
     *
     * @param uuid
     * @return
     */
    int deleteNotifyTransactionMessageByUUID(String uuid);

    /**
     *
     * @param entity
     * @return
     */
    NotifyTransactionMessage update(NotifyTransactionMessage entity);

    /**
     * 删除消息根据UUID和队列名称
     *
     * @param uuid
     * @param consumerQueue
     */
    int deleteNotifyTransactionMessageByUUIDAndQueue(String uuid, String consumerQueue);
}
