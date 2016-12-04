package com.guwr.accumulate.queue.notify.message.scheduled;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.queue.notify.message.scheduled.IMessageScheduled
 * Date         2016/11/26
 * Time         16:49
 * Description
 */
public interface IMessageScheduled {
    /**
     * 处理状态为“待确认”但已超时的消息.
     */
    void handleWaitingConfirmTimeOutMessages();
    /**
     * 处理状态为“已确认”但超时没有被成功消费确认的消息
     */
    void handleAlreadyConfirmTimeOutMessages();
}