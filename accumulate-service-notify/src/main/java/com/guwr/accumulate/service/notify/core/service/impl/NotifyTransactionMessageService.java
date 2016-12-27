package com.guwr.accumulate.service.notify.core.service.impl;

import com.guwr.accumulate.common.page.PageBean;
import com.guwr.accumulate.common.page.PageParam;
import com.guwr.accumulate.facade.notify.entity.NotifyTransactionMessage;
import com.guwr.accumulate.facade.notify.enums.NotifyTransactionMessageStatus;
import com.guwr.accumulate.facade.notify.vo.NotifyTransactionMessageVO;
import com.guwr.accumulate.service.notify.core.dao.NotifyTransactionMessageRepository;
import com.guwr.accumulate.service.notify.core.service.INotifyTransactionMessageService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.notify.service.impl.NotifyTransactionMessageService
 * Date 2016/8/13
 * Time 21:16
 */
@Service
public class NotifyTransactionMessageService implements INotifyTransactionMessageService {

    private static Logger logger = LoggerFactory.getLogger(NotifyTransactionMessageService.class);
    @Autowired
    private NotifyTransactionMessageRepository repository;
    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public NotifyTransactionMessage saveNotifyTransactionMessage(NotifyTransactionMessage entity) {
        Date date = new Date();
        entity.setCreateTime(date);
        entity.setUpdateTime(date);
        entity.setAreadlyDead(1);
        entity.setMessageSendTimes(0);
        entity.setStatus(NotifyTransactionMessageStatus.WAITING_CONFIRM.getValue());
        return repository.save(entity);
    }

    @Override
    public NotifyTransactionMessage saveNotifyTransactionMessageAndFlush(NotifyTransactionMessage entity) {
        NotifyTransactionMessage notifyTransactionMessage = saveNotifyTransactionMessage(entity);
        repository.flush();
        return notifyTransactionMessage;
    }

    @Override
    public void sendNotifyTransactionMessage(Integer id) {
        final NotifyTransactionMessage entity = repository.findOne(id);
        if (entity == null) {
            throw new RuntimeException("entity is null");
        }
        entity.setStatus(NotifyTransactionMessageStatus.ALREADY_CONFIRM.getValue());
        repository.save(entity);
        jmsTemplate.setDefaultDestinationName(entity.getConsumerQueue());
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(entity.getMessageBody());
            }
        });
    }

    @Override
    public void sendNotifyTransactionMessage(final NotifyTransactionMessage entity) {
        entity.setStatus(NotifyTransactionMessageStatus.ALREADY_CONFIRM.getValue());
        repository.save(entity);
        jmsTemplate.setDefaultDestinationName(entity.getConsumerQueue());
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(entity.getMessageBody());
            }
        });
    }

    @Override
    public NotifyTransactionMessage saveAndSendNotifyTransactionMessage(NotifyTransactionMessage entity) {
        Date date = new Date();
        entity.setCreateTime(date);
        entity.setUpdateTime(date);
        entity.setAreadlyDead(1);
        entity.setMessageSendTimes(0);
        entity.setStatus(NotifyTransactionMessageStatus.ALREADY_CONFIRM.getValue());
        final NotifyTransactionMessage notifyTransactionMessage = saveNotifyTransactionMessage(entity);
        jmsTemplate.setDefaultDestinationName(notifyTransactionMessage.getConsumerQueue());
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(notifyTransactionMessage.getMessageBody());
            }
        });
        return notifyTransactionMessage;
    }

    @Override
    public void directSendNotifyTransactionMessage(final NotifyTransactionMessage entity) {
        jmsTemplate.setDefaultDestinationName(entity.getConsumerQueue());
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(entity.getMessageBody());
            }
        });
    }

    @Override
    public void repeatSendNotifyTransactionMessage(final NotifyTransactionMessage entity) {
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
    public void repeatSendNotifyTransactionMessageById(Integer id) {
        final NotifyTransactionMessage entity = repository.findOne(id);
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
    public void repeatSendNotifyTransactionMessageByDestinationName(String destinationName, Integer batchSize) {
        NotifyTransactionMessageVO info = new NotifyTransactionMessageVO();
        info.setSortType(NotifyTransactionMessageVO.SORT_TYPE_ASC);
        PageBean<NotifyTransactionMessage> listPage = findListPageByCondition(info);
        List<NotifyTransactionMessage> recordList = listPage.getRecordList();

        Map<Integer, NotifyTransactionMessage> messageMap = new HashMap<>();

        for (final NotifyTransactionMessage notifyTransactionMessage : recordList) {
            Integer id = notifyTransactionMessage.getId();
            messageMap.put(id, notifyTransactionMessage);
        }

        for (Map.Entry<Integer, NotifyTransactionMessage> messageEntry : messageMap.entrySet()) {
            final NotifyTransactionMessage notifyTransactionMessage = messageEntry.getValue();

            notifyTransactionMessage.setUpdateTime(new Date());
            notifyTransactionMessage.setMessageSendTimes(notifyTransactionMessage.getMessageSendTimes() + 1);
            repository.save(notifyTransactionMessage);

            jmsTemplate.setDefaultDestinationName(notifyTransactionMessage.getConsumerQueue());
            jmsTemplate.send(new MessageCreator() {
                public Message createMessage(Session session) throws JMSException {
                    return session.createTextMessage(notifyTransactionMessage.getMessageBody());
                }
            });
        }
    }

    @Override
    public void deleteNotifyTransactionMessageById(Integer id) {
        repository.delete(id);
    }

    @Override
    public PageBean<NotifyTransactionMessage> findListPageByCondition(NotifyTransactionMessageVO info) {
        PageBean<NotifyTransactionMessage> pageBean;
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
        conditionSql.append(" tbl_notify_transaction_message a");
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
        List<NotifyTransactionMessage> recordList = repository.listPage(listPageStr, pageParam);
        pageBean = new PageBean<>(pageNum, numPerPage, total, recordList);
        return pageBean;
    }


    @Override
    @Transactional
    public NotifyTransactionMessage update(NotifyTransactionMessage entity) {
        entity.setUpdateTime(new Date());
        return repository.save(entity);
    }

    @Override
    @Transactional
    public int deleteNotifyTransactionMessageByUUID(String uuid) {
        return repository.deleteNotifyTransactionMessageByUUID(uuid);
    }

    @Override
    @Transactional
    public int deleteNotifyTransactionMessageByUUIDAndQueue(String uuid, String consumerQueue) {
        return repository.deleteNotifyTransactionMessageByUUIDAndQueue(uuid, consumerQueue);
    }
}
