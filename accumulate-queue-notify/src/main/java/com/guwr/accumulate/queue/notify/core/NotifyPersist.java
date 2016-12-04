package com.guwr.accumulate.queue.notify.core;

import com.guwr.accumulate.common.page.PageBean;
import com.guwr.accumulate.facade.notify.entity.NotifyRecord;
import com.guwr.accumulate.facade.notify.entity.NotifyRecordLog;
import com.guwr.accumulate.facade.notify.facade.INotifyRecordFacade;
import com.guwr.accumulate.facade.notify.facade.INotifyRecordLogFacade;
import com.guwr.accumulate.facade.notify.vo.NotifyRecordVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.queue.notify.core.NotifyPersist
 * Date         2016/11/6
 * Time         15:12
 * Description
 */
@Component
public class NotifyPersist implements Serializable {

    private static final long serialVersionUID = 7888690426545966517L;

    private static Logger logger = LoggerFactory.getLogger(NotifyPersist.class);

    @Autowired
    private INotifyRecordFacade notifyRecordFacade;
    @Autowired
    private INotifyRecordLogFacade notifyRecordLogFacade;

    public Integer saveNotifyRecord(NotifyRecord entity) {
        logger.info("NotifyPersist.saveNotifyRecord");
        NotifyRecord record = notifyRecordFacade.save(entity);
        return record.getId();
    }

    public void updateNotifyRord(Integer id, Integer notifyTimes, Integer status) {
        logger.info("NotifyPersist.updateNotifyRord");
        NotifyRecord entity = notifyRecordFacade.findOne(id);
        entity.setNotifyTimes(notifyTimes);
        entity.setStatus(status);
        notifyRecordFacade.update(entity);
    }

    public Integer saveNotifyRecordLogs(Integer notifyId, String merchantNo, String merchantOrderNo, String request, String response,
                                        Integer httpStatus) {
        logger.info("NotifyPersist.saveNotifyRecordLogs");
        NotifyRecordLog entity = new NotifyRecordLog();
        entity.setHttpStatus(httpStatus);
        entity.setMerchantNo(merchantNo);
        entity.setMerchantOrderNo(merchantOrderNo);
        entity.setNotifyId(notifyId);
        entity.setRequest(request);
        entity.setResponse(response);
        NotifyRecordLog recordLog = notifyRecordLogFacade.save(entity);
        return recordLog.getId();
    }

    public PageBean<NotifyRecord> findListPage(NotifyRecordVO info) {
        logger.info("NotifyPersist.findListPageByCondition");
        PageBean<NotifyRecord> pageBean = notifyRecordFacade.findListPage(info);
        return pageBean;
    }
}
