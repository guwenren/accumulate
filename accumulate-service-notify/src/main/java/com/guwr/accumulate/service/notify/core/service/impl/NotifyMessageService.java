package com.guwr.accumulate.service.notify.core.service.impl;


import com.guwr.accumulate.common.page.PageBean;
import com.guwr.accumulate.facade.notify.entity.NotifyMessage;
import com.guwr.accumulate.service.notify.core.dao.NotifyMessageRepository;
import com.guwr.accumulate.service.notify.core.service.INotifyMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    @Override
    public Integer countNotifyMessage() {
        return null;
    }

    @Override
    public NotifyMessage getNotifyMessage(Integer id) {
        return null;
    }

    @Override
    public List<NotifyMessage> listNotifyMessage() {
        return null;
    }

    @Override
    public PageBean<NotifyMessage> pageNotifyMessage() {
        return null;
    }

    @Override
    public void removeNotifyMessage(Integer id) {

    }

    @Override
    public NotifyMessage updateNotifyMessage(NotifyMessage entity) {
        return null;
    }

    @Override
    public NotifyMessage save(NotifyMessage entity) {
        logger.info("save.entity = {}", entity);
        Date date = new Date();
        entity.setCreateTime(date);
        entity.setUpdateTime(date);
        return repository.save(entity);
    }

    @Override
    public NotifyMessage findOne(Integer id) {
        return repository.findOne(id);
    }
}
