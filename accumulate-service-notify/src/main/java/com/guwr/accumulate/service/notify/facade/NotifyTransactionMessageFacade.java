package com.guwr.accumulate.service.notify.facade;

import com.guwr.accumulate.common.enums.NotifyDataType;
import com.guwr.accumulate.common.page.PageBean;
import com.guwr.accumulate.facade.notify.entity.NotifyTransactionMessage;
import com.guwr.accumulate.facade.notify.facade.INotifyTransactionMessageFacade;
import com.guwr.accumulate.facade.notify.vo.NotifyTransactionMessageVO;
import com.guwr.accumulate.service.notify.core.service.INotifyTransactionMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.notify.facade.NotifyTransactionMessageFacade
 * Date 2016/8/21
 * Time 18:31
 */
@Component
public class NotifyTransactionMessageFacade implements INotifyTransactionMessageFacade {
    private static Logger logger = LoggerFactory.getLogger(NotifyTransactionMessageFacade.class);
    @Autowired
    private INotifyTransactionMessageService service;

    @Override
    public PageBean<NotifyTransactionMessage> findListPageByCondition(NotifyTransactionMessageVO info) {
        logger.info("findListPageByCondition.info = [" + info + "]");
        return service.findListPageByCondition(info);
    }

    @Override
    public NotifyTransactionMessage saveNotifyTransactionMessage(NotifyTransactionMessageVO info) {
        logger.info("saveNotifyTransactionMessage.info = [" + info + "]");
        String uuid = info.getUuid();
        String consumerQueue = info.getConsumerQueue();
        String messageBody = info.getMessageBody();
        NotifyTransactionMessage entity = new NotifyTransactionMessage();
        entity.setUuid(uuid);
        entity.setConsumerQueue(consumerQueue);
        entity.setMessageBody(messageBody);
        entity.setMessageDataType(NotifyDataType.JSON.name());
        return service.saveNotifyTransactionMessage(entity);
    }

    @Override
    public NotifyTransactionMessage saveNotifyTransactionMessageAndFlush(NotifyTransactionMessageVO info) {
        logger.info("saveNotifyTransactionMessage.info = [" + info + "]");
        String uuid = info.getUuid();
        String consumerQueue = info.getConsumerQueue();
        String messageBody = info.getMessageBody();
        NotifyTransactionMessage entity = new NotifyTransactionMessage();
        entity.setUuid(uuid);
        entity.setConsumerQueue(consumerQueue);
        entity.setMessageBody(messageBody);
        entity.setMessageDataType(NotifyDataType.JSON.name());
        return service.saveNotifyTransactionMessageAndFlush(entity);
    }



    @Override
    public NotifyTransactionMessage findNotifyTransactionMessageByUUID(String uuid) {
        return null;
    }

    @Override
    public void sendNotifyTransactionMessage(Integer id) {
        logger.info("sendNotifyTransactionMessage.id = [" + id + "]");
        service.sendNotifyTransactionMessage(id);
    }

    @Override
    public void sendNotifyTransactionMessage(NotifyTransactionMessage entity) {
        logger.info("sendNotifyTransactionMessage.entity = [" + entity + "]");
        service.sendNotifyTransactionMessage(entity);
    }

    @Override
    public NotifyTransactionMessage saveAndSendNotifyTransactionMessage(NotifyTransactionMessageVO info) {
        logger.info("saveAndSendNotifyTransactionMessage.info = [" + info + "]");
        String uuid = info.getUuid();
        String consumerQueue = info.getConsumerQueue();
        String messageBody = info.getMessageBody();
        NotifyTransactionMessage entity = new NotifyTransactionMessage();
        entity.setUuid(uuid);
        entity.setConsumerQueue(consumerQueue);
        entity.setMessageBody(messageBody);
        entity.setMessageDataType(NotifyDataType.JSON.name());
        return service.saveAndSendNotifyTransactionMessage(entity);
    }

    @Override
    public void deleteNotifyTransactionMessageById(Integer id) {
        logger.info("deleteNotifyTransactionMessageById.id = [" + id + "]");
        service.deleteNotifyTransactionMessageById(id);
    }

    @Override
    public void deleteNotifyTransactionMessageByUUID(String uuid) {
        logger.info("deleteNotifyTransactionMessageByUUID.uuid = [" + uuid + "]");
        service.deleteNotifyTransactionMessageByUUID(uuid);
    }

    @Override
    public void deleteNotifyTransactionMessageByUUIDAndQueue(String uuid, String consumerQueue) {
        logger.info("deleteNotifyTransactionMessageByUUIDAndQueue.uuid = [" + uuid + "],consumerQueue = [" + consumerQueue + "]");
        service.deleteNotifyTransactionMessageByUUIDAndQueue(uuid,consumerQueue);
    }


    @Override
    public NotifyTransactionMessage update(NotifyTransactionMessage entity) {
        logger.info("update.entity = [" + entity + "]");
        return service.update(entity);
    }

    @Override
    public void repeatSendNotifyTransactionMessage(NotifyTransactionMessage entity) {
        logger.info("repeatSendNotifyTransactionMessage.entity = [" + entity + "]");
        service.repeatSendNotifyTransactionMessage(entity);
    }
}
