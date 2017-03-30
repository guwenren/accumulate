package com.guwr.accumulate.service.notify.core.service.impl;


import com.guwr.accumulate.common.page.PageBean;
import com.guwr.accumulate.common.page.PageParam;
import com.guwr.accumulate.facade.notify.entity.NotifyRecord;
import com.guwr.accumulate.facade.notify.enums.NotifyRecordEnum;
import com.guwr.accumulate.facade.notify.vo.NotifyRecordVO;
import com.guwr.accumulate.service.notify.core.dao.NotifyRecordRepository;
import com.guwr.accumulate.service.notify.core.service.INotifyRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.Date;
import java.util.List;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.notify.service.impl.NotifyRecordService
 * Date 2016/8/13
 * Time 21:16
 */
@Service
public class NotifyRecordService implements INotifyRecordService {

    private static Logger logger = LoggerFactory.getLogger(NotifyRecordService.class);
    @Autowired
    private NotifyRecordRepository repository;
    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public NotifyRecord save(NotifyRecord entity) {
        logger.info("NotifyRecordService.saveNotifyMessage");
        return repository.save(entity);
    }

    @Override
    public PageBean<NotifyRecord> findListPage(NotifyRecordVO info) {
        PageBean<NotifyRecord> pageBean;
        Integer pageNum = info.getPageNum();
        Integer numPerPage = info.getNumPerPage();
        PageParam pageParam = new PageParam(pageNum, numPerPage);
        StringBuilder conditionSql = new StringBuilder();
        String listPage = " select id,version,create_time createTime,last_notify_time lastNotifyTime,notify_times notifyTimes,limit_notify_times limitNotifyTimes,url,merchant_no merchantNo,merchant_order_no merchantOrderNo,notify_type notifyType,status,uuid from ";
        String listPageCount = " select count(1) from ";
        conditionSql.append(" tbl_notify_record a");
        conditionSql.append(" where 1 = 1");
        List<Integer> status = info.getStatus();
        List<Integer> notifyTimes = info.getNotifyTimes();
        if (!CollectionUtils.isEmpty(status)) {
            conditionSql.append(" and a.status in (");
            for (Integer statu : status) {
                conditionSql.append(statu).append(",");
            }
            conditionSql.deleteCharAt(conditionSql.length() - 1);
            conditionSql.append(" )");
        }
        if (!CollectionUtils.isEmpty(notifyTimes)) {
            conditionSql.append(" and a.notify_times in (");
            for (Integer notifyTime : notifyTimes) {
                conditionSql.append(notifyTime).append(",");
            }
            conditionSql.deleteCharAt(conditionSql.length() - 1);
            conditionSql.append(" )");
        }
        String listPageCountStr = listPageCount + conditionSql.toString();
        int total = repository.listPageCount(listPageCountStr);
        if (total <= 0) {
            return new PageBean<>(pageNum, numPerPage, total, null);
        }
        String listPageStr = listPage + conditionSql.toString();
        List<NotifyRecord> recordList = repository.listPage(listPageStr, pageParam);
        pageBean = new PageBean<>(pageNum, numPerPage, total, recordList);
        return pageBean;
    }

    @Override
    public void sendMessage(int nid) {
        Date date = new Date();
        final NotifyRecord notifyRecord = findOne(nid);
        notifyRecord.setStatus(NotifyRecordEnum.NotifyRecordStatus.SENDING.getValue());
        notifyRecord.setUpdateTime(date);
        jmsTemplate.setDefaultDestinationName("NotifyQueue");
        save(notifyRecord);
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(notifyRecord.getUrl());
            }
        });
    }


    @Override
    public NotifyRecord update(NotifyRecord entity) {
        logger.info("NotifyRecordService.updateNotifyRecord");
        return repository.save(entity);
    }

    @Override
    public NotifyRecord findOne(Integer id) {
        return repository.findOne(id);
    }
}
