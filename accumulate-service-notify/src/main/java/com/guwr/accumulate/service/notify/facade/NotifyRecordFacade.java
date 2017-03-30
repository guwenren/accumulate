package com.guwr.accumulate.service.notify.facade;

import com.guwr.accumulate.common.page.PageBean;
import com.guwr.accumulate.facade.notify.entity.NotifyRecord;
import com.guwr.accumulate.facade.notify.facade.INotifyRecordFacade;
import com.guwr.accumulate.facade.notify.vo.NotifyRecordVO;
import com.guwr.accumulate.service.notify.core.service.INotifyRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.notify.facade.NotifyRecordFacade
 * Date 2016/8/21
 * Time 18:31
 */
@Component
public class NotifyRecordFacade implements INotifyRecordFacade {
    private static Logger logger = LoggerFactory.getLogger(NotifyRecordFacade.class);
    @Autowired
    private INotifyRecordService service;

    @Override
    public NotifyRecord saveNotifyRecord(NotifyRecord entity) {
        return service.save(entity);
    }

    @Override
    public NotifyRecord findOne(Integer id) {
        logger.info("id = [" + id + "]");
        return service.findOne(id);
    }

    @Override
    public NotifyRecord updateNotifyRecord(NotifyRecord entity) {
        return service.update(entity);
    }

    @Override
    public PageBean<NotifyRecord> findListPage(NotifyRecordVO vo) {
        logger.info("vo = [" + vo + "]");
        return service.findListPage(vo);
    }
}
