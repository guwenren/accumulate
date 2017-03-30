package com.guwr.accumulate.service.notify.core.service.impl;

import com.guwr.accumulate.common.page.PageBean;
import com.guwr.accumulate.common.page.PageParam;
import com.guwr.accumulate.facade.notify.entity.NotifyMessage;
import com.guwr.accumulate.facade.notify.enums.NotifyMessageStatus;
import com.guwr.accumulate.facade.notify.vo.NotifyMessageVO;
import com.guwr.accumulate.service.notify.core.dao.NotifyMessageRepository;
import com.guwr.accumulate.service.notify.core.service.INotifyMessageService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.notify.service.impl.NotifyMessageService
 * Date 2016/8/13
 * Time 21:16
 */
@Service
public class NotifyMessageService implements INotifyMessageService {

    private static Logger logger = LoggerFactory.getLogger(NotifyMessageService.class);
    @Autowired
    private NotifyMessageRepository repository;
    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public NotifyMessage saveNotifyMessage(NotifyMessage entity) {
        Date date = new Date();
        entity.setCreateTime(date);
        entity.setUpdateTime(date);
        entity.setAreadlyDead(1);
        entity.setMessageSendTimes(0);
        entity.setStatus(NotifyMessageStatus.WAITING_CONFIRM.getValue());
        return repository.save(entity);
    }

    @Override
    public NotifyMessage saveNotifyMessageAndFlush(NotifyMessage entity) {
        NotifyMessage notifyMessage = saveNotifyMessage(entity);
        repository.flush();
        return notifyMessage;
    }

    @Override
    public void sendNotifyMessage(Integer id) {
        final NotifyMessage entity = repository.findOne(id);
        if (entity == null) {
            throw new RuntimeException("entity is null");
        }
        entity.setStatus(NotifyMessageStatus.ALREADY_CONFIRM.getValue());
        repository.save(entity);
        jmsTemplate.setDefaultDestinationName(entity.getConsumerQueue());
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(entity.getMessageBody());
            }
        });
    }

    @Override
    public void sendNotifyMessage(final NotifyMessage entity) {
        entity.setStatus(NotifyMessageStatus.ALREADY_CONFIRM.getValue());
        repository.save(entity);
        jmsTemplate.setDefaultDestinationName(entity.getConsumerQueue());
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(entity.getMessageBody());
            }
        });
    }

    @Override
    public NotifyMessage saveAndSendNotifyMessage(NotifyMessage entity) {
        Date date = new Date();
        entity.setCreateTime(date);
        entity.setUpdateTime(date);
        entity.setAreadlyDead(1);
        entity.setMessageSendTimes(0);
        entity.setStatus(NotifyMessageStatus.ALREADY_CONFIRM.getValue());
        final NotifyMessage notifyMessage = repository.save(entity);
        jmsTemplate.setDefaultDestinationName(notifyMessage.getConsumerQueue());
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(notifyMessage.getMessageBody());
            }
        });
        return notifyMessage;
    }

    @Override
    public void directSendNotifyMessage(final NotifyMessage entity) {
        jmsTemplate.setDefaultDestinationName(entity.getConsumerQueue());
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(entity.getMessageBody());
            }
        });
    }

    @Override
    public void repeatSendNotifyMessage(final NotifyMessage entity) {
        Integer messageSendTimes = entity.getMessageSendTimes();
        entity.setMessageSendTimes(messageSendTimes + 1);
        entity.setUpdateTime(new Date());
        repository.save(entity);
        jmsTemplate.setDefaultDestinationName(entity.getConsumerQueue());
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(entity.getMessageBody());
            }
        });
    }

    @Override
    public void repeatSendNotifyMessageById(Integer id) {
        final NotifyMessage entity = repository.findOne(id);
        Integer messageSendTimes = entity.getMessageSendTimes();
        entity.setMessageSendTimes(messageSendTimes + 1);
        entity.setUpdateTime(new Date());
        repository.save(entity);
        jmsTemplate.setDefaultDestinationName(entity.getConsumerQueue());
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(entity.getMessageBody());
            }
        });
    }

    @Override
    public void repeatSendNotifyMessageByDestinationName(String destinationName, Integer batchSize) {
        NotifyMessageVO info = new NotifyMessageVO();
        info.setSortType(NotifyMessageVO.SORT_TYPE_ASC);
        PageBean<NotifyMessage> listPage = findListPageByCondition(info);
        List<NotifyMessage> recordList = listPage.getRecordList();

        Map<Integer, NotifyMessage> messageMap = new HashMap<>();

        for (final NotifyMessage notifyMessage : recordList) {
            Integer id = notifyMessage.getId();
            messageMap.put(id, notifyMessage);
        }

        for (Map.Entry<Integer, NotifyMessage> messageEntry : messageMap.entrySet()) {
            final NotifyMessage notifyMessage = messageEntry.getValue();

            notifyMessage.setUpdateTime(new Date());
            notifyMessage.setMessageSendTimes(notifyMessage.getMessageSendTimes() + 1);
            repository.save(notifyMessage);

            jmsTemplate.setDefaultDestinationName(notifyMessage.getConsumerQueue());
            jmsTemplate.send(new MessageCreator() {
                public Message createMessage(Session session) throws JMSException {
                    return session.createTextMessage(notifyMessage.getMessageBody());
                }
            });
        }
    }

    @Override
    public void deleteNotifyMessageById(Integer id) {
        repository.delete(id);
    }

    @Override
    public PageBean<NotifyMessage> findListPageByCondition(NotifyMessageVO info) {
        PageBean<NotifyMessage> pageBean;
        Integer pageNum = info.getPageNum();
        Integer numPerPage = info.getNumPerPage();
        Integer areadlyDead = info.getAreadlyDead();
        String consumerQueue = info.getConsumerQueue();
        String sortType = info.getSortType();
        Integer status = info.getStatus();
        String createTimeBefore = info.getCreateTimeBefore();

        PageParam pageParam = new PageParam(pageNum, numPerPage);
        StringBuilder conditionSql = new StringBuilder();
        String listPage = " select  id,version,create_time createTime,update_time updateTime,uuid,  message_body messageBody,message_data_type messageDataType,consumer_queue consumerQueue,message_send_times messageSendTimes,areadly_dead areadlyDead, status,remark  from ";
        String listPageCount = " select count(1) from ";
        conditionSql.append(" tbl_notify__message a");
        conditionSql.append(" where 1 = 1");

        String orderBy = " order by a.id " + sortType;
        if (StringUtils.isNotBlank(consumerQueue)) {
            conditionSql.append(" and a.consumer_queue = '").append(consumerQueue).append("'");
        }
        if (areadlyDead != null) {
            conditionSql.append(" and a.areadly_dead =").append(areadlyDead);
        }
        if (status != null) {
            conditionSql.append(" and a.status =").append(status);
        }
        if (StringUtils.isNotBlank(createTimeBefore)) {
            conditionSql.append(" and a.create_time < '").append(createTimeBefore).append("'");
        }
        String listPageCountStr = listPageCount + conditionSql.toString();
        logger.info(listPageCountStr);
        int total = repository.listPageCount(listPageCountStr);
        if (total <= 0) {
            return new PageBean<>(pageNum, numPerPage, total, null);
        }
        String listPageStr = listPage + conditionSql.toString() + orderBy;
        logger.info(listPageStr);
        List<NotifyMessage> recordList = repository.listPage(listPageStr, pageParam);
        pageBean = new PageBean<>(pageNum, numPerPage, total, recordList);
        return pageBean;
    }


    @Override
    @Transactional
    public NotifyMessage update(NotifyMessage entity) {
        entity.setUpdateTime(new Date());
        return repository.save(entity);
    }

    @Override
    @Transactional
    public int deleteNotifyMessageByUUID(String uuid) {
        return repository.deleteNotifyMessageByUUID(uuid);
    }

    @Override
    @Transactional
    public int deleteNotifyMessageByUUIDAndQueue(String uuid, String consumerQueue) {
        return repository.deleteNotifyMessageByUUIDAndQueue(uuid, consumerQueue);
    }
}
