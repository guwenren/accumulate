package com.guwr.accumulate.service.notify.facade;

import com.guwr.accumulate.common.page.PageBean;
import com.guwr.accumulate.common.web.vo.BaseVO;
import com.guwr.accumulate.facade.notify.entity.NotifyMessage;
import com.guwr.accumulate.facade.notify.facade.INotifyMessageFacade;
import com.guwr.accumulate.facade.notify.vo.NotifyMessageVO;
import com.guwr.accumulate.service.notify.core.service.INotifyMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.notify.facade.NotifyMessageFacade
 * Date 2016/8/21
 * Time 18:31
 */
@Component
public class NotifyMessageFacade implements INotifyMessageFacade {
    private static Logger logger = LoggerFactory.getLogger(NotifyMessageFacade.class);
    @Autowired
    private INotifyMessageService service;

    @Override
    public PageBean<NotifyMessage> findListPageByCondition(NotifyMessageVO info) {
        logger.info("findListPageByCondition.info = [" + info + "]");
        return service.findListPageByCondition(info);
    }

    @Override
    public NotifyMessage saveNotifyMessage(NotifyMessageVO info) {
        logger.info("saveNotifyMessage.info = [" + info + "]");
        String uuid = info.getUuid();
        String consumerQueue = info.getConsumerQueue();
        String messageBody = info.getMessageBody();
        NotifyMessage entity = new NotifyMessage();
        entity.setUuid(uuid);
        entity.setConsumerQueue(consumerQueue);
        entity.setMessageBody(messageBody);
        return service.saveNotifyMessage(entity);
    }

    @Override
    public NotifyMessage saveNotifyMessageAndFlush(NotifyMessageVO info) {
        logger.info("saveNotifyMessage.info = [" + info + "]");
        String uuid = info.getUuid();
        String consumerQueue = info.getConsumerQueue();
        String messageBody = info.getMessageBody();
        NotifyMessage entity = new NotifyMessage();
        entity.setUuid(uuid);
        entity.setConsumerQueue(consumerQueue);
        entity.setMessageBody(messageBody);
        return service.saveNotifyMessageAndFlush(entity);
    }


    @Override
    public NotifyMessage findNotifyMessageByUUID(String uuid) {
        return null;
    }

    @Override
    public void sendNotifyMessage(Integer id) {
        logger.info("sendNotifyMessage.id = [" + id + "]");
        service.sendNotifyMessage(id);
    }

    @Override
    public void sendNotifyMessage(NotifyMessage entity) {
        logger.info("sendNotifyMessage.entity = [" + entity + "]");
        service.sendNotifyMessage(entity);
    }

    @Override
    public NotifyMessage saveAndSendNotifyMessage(NotifyMessageVO info) {
        logger.info("saveAndSendNotifyMessage.info = [" + info + "]");
        String uuid = info.getUuid();
        String consumerQueue = info.getConsumerQueue();
        String messageBody = info.getMessageBody();
        NotifyMessage entity = new NotifyMessage();
        entity.setUuid(uuid);
        entity.setConsumerQueue(consumerQueue);
        entity.setMessageBody(messageBody);
        return service.saveAndSendNotifyMessage(entity);
    }

    @Override
    public void deleteNotifyMessageById(Integer id) {
        logger.info("deleteNotifyMessageById.id = [" + id + "]");
        service.deleteNotifyMessageById(id);
    }

    @Override
    public void deleteNotifyMessageByUUID(String uuid) {
        logger.info("deleteNotifyMessageByUUID.uuid = [" + uuid + "]");
        service.deleteNotifyMessageByUUID(uuid);
    }

    @Override
    public void deleteNotifyMessageByUUIDAndQueue(String uuid, String consumerQueue) {
        logger.info("uuid_{}_consumerQueue{}_通过uuid删除队列消息", uuid, consumerQueue);
        service.deleteNotifyMessageByUUIDAndQueue(uuid, consumerQueue);
    }

    @Override
    public void deleteNotifyMessageByUUIDAndQueue(BaseVO info) {
        String uuid = info.getUuid();
        String consumerQueue = info.getConsumerQueue();
        service.deleteNotifyMessageByUUIDAndQueue(uuid, consumerQueue);
    }

    @Override
    public NotifyMessage update(NotifyMessage entity) {
        logger.info("updateNotifyRecord.entity = [" + entity + "]");
        return service.update(entity);
    }

    @Override
    public void repeatSendNotifyMessage(NotifyMessage entity) {
        logger.info("repeatSendNotifyMessage.entity = [" + entity + "]");
        service.repeatSendNotifyMessage(entity);
    }
}
