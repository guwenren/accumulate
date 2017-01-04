package com.guwr.accumulate.queue.notify.message.scheduled.impl;

import com.alibaba.fastjson.JSON;
import com.guwr.accumulate.common.config.PublicConfig;
import com.guwr.accumulate.common.page.PageBean;
import com.guwr.accumulate.facade.notify.entity.NotifyTransactionMessage;
import com.guwr.accumulate.facade.notify.enums.NotifyTransactionMessageStatus;
import com.guwr.accumulate.facade.notify.facade.INotifyTransactionMessageFacade;
import com.guwr.accumulate.facade.notify.vo.NotifyTransactionMessageVO;
import com.guwr.accumulate.facade.wmps.entity.ProductRecord;
import com.guwr.accumulate.facade.wmps.facade.IProductRecordFacade;
import com.guwr.accumulate.queue.notify.message.scheduled.IMessageScheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.queue.notify.message.scheduled.impl.MessageScheduled
 * Date         2016/11/26
 * Time         16:49
 * Description
 */
@Component
public class MessageScheduled implements IMessageScheduled {

    private static Logger logger = LoggerFactory.getLogger(MessageScheduled.class);
    @Autowired
    private INotifyTransactionMessageFacade notifyTransactionMessageFacade;
    @Autowired
    private IProductRecordFacade productRecordFacade;

    @Override
    public void handleWaitingConfirmTimeOutMessages() {
        NotifyTransactionMessageVO info = new NotifyTransactionMessageVO();
        info.setStatus(NotifyTransactionMessageStatus.WAITING_CONFIRM.getValue());
        info.setNumPerPage(50);
        info.setSortType(NotifyTransactionMessageVO.SORT_TYPE_ASC);
        String createTimeBefore = getCreateTimeBefore();
        info.setCreateTimeBefore(createTimeBefore);
        PageBean<NotifyTransactionMessage> listPageByCondition = notifyTransactionMessageFacade.findListPageByCondition(info);

        logger.info("listPageByCondition = " + JSON.toJSONString(listPageByCondition));

        List<NotifyTransactionMessage> notifyTransactionMessages = listPageByCondition.getRecordList();

        if (CollectionUtils.isEmpty(notifyTransactionMessages)) {
            logger.info("没有[waiting_confirm]状态的消息");
            return;
        }

        logger.info("开始处理[waiting_confirm]状态的消息,总条数[" + notifyTransactionMessages.size() + "]");
        for (NotifyTransactionMessage message : notifyTransactionMessages) {
            Integer id = message.getId();
            String uuid = message.getUuid();
            ProductRecord productRecord = productRecordFacade.findOneProductRecordByUUID(uuid);
            if (productRecord == null) {
                // 投资没有成功，可以直接删除数据
                logger.debug("投资没有成功,删除[waiting_confirm]消息id[" + id + "]的消息");
                notifyTransactionMessageFacade.deleteNotifyTransactionMessageById(id);
            } else {
                logger.debug("投资成功,重新发送消息id[" + id + "]的消息");
                notifyTransactionMessageFacade.sendNotifyTransactionMessage(id);
            }
        }
    }

    @Override
    public void handleAlreadyConfirmTimeOutMessages() {
        NotifyTransactionMessageVO info = new NotifyTransactionMessageVO();
        info.setStatus(NotifyTransactionMessageStatus.ALREADY_CONFIRM.getValue());
        info.setNumPerPage(50);
        info.setSortType(NotifyTransactionMessageVO.SORT_TYPE_ASC);
        info.setAreadlyDead(1);
        String createTimeBefore = getCreateTimeBefore();
        info.setCreateTimeBefore(createTimeBefore);
        PageBean<NotifyTransactionMessage> listPageByCondition = notifyTransactionMessageFacade.findListPageByCondition(info);

        logger.info("listPageByCondition = " + JSON.toJSONString(listPageByCondition));
        List<NotifyTransactionMessage> notifyTransactionMessages = listPageByCondition.getRecordList();

        if (CollectionUtils.isEmpty(notifyTransactionMessages)) {
            logger.info("没有[already_confirm]状态的消息");
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        logger.info("开始处理[already_confirm]状态的消息,总条数[" + notifyTransactionMessages.size() + "]");

        Integer messageMaxSendTimes = PublicConfig.MESSAGE_MAX_SEND_TIMES;

        Map<Integer, Integer> notifyParam = getNotifyParam();

        for (NotifyTransactionMessage message : notifyTransactionMessages) {
            Integer id = message.getId();
            long time = message.getUpdateTime().getTime();
            Integer messageSendTimes = message.getMessageSendTimes();
//            String consumerQueue = message.getConsumerQueue();
            logger.info("开始处理[already_confirm]消息ID为[" + id + "]的消息");

            logger.info("[already_confirm]消息ID为[" + id + "]的消息,已经重新发送的次数[" + messageSendTimes + "]");
            if (messageMaxSendTimes < messageSendTimes) {  //判断发送次数, 大于最大发送次数
                message.setAreadlyDead(0); //将消息标记为死亡
                notifyTransactionMessageFacade.update(message);
                continue;
            }

            Integer times = notifyParam.get(messageSendTimes); // 获取发送次数对应的发送间隔时间
            long needTime = System.currentTimeMillis() - times * 60 * 1000;
            if (time > needTime) { // 判断是否达到了可以再次发送的时间条件
                logger.info("currentTime[" + sdf.format(needTime) + "],[already_confirm]消息上次发送时间[" + sdf.format(time) + "],必须过了[" + times + "]分钟才可以再发送。");
                continue;
            }
            // 重新发送消息
            notifyTransactionMessageFacade.repeatSendNotifyTransactionMessage(message);
            logger.info("结束处理[already_confirm]消息ID为[" + id + "]的消息");
        }
    }

    /**
     * 获取配置的开始处理的时间
     *
     * @return
     */
    private String getCreateTimeBefore() {
        Integer duration = PublicConfig.MESSAGE_HANDLE_DURATION;
        long currentTimeInMillis = System.currentTimeMillis();
        Date date = new Date(currentTimeInMillis - Integer.valueOf(duration) * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * 根据配置获取通知间隔时间
     *
     * @return
     */
    private Map<Integer, Integer> getNotifyParam() {
        Map<Integer, Integer> notifyParam = new HashMap<>();
        notifyParam.put(0, 1);
        notifyParam.put(1, 1);
        notifyParam.put(2, 2);
        notifyParam.put(3, 3);
        notifyParam.put(4, 4);
        notifyParam.put(5, 5);
        return notifyParam;
    }
}
