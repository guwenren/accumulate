package com.guwr.accumulate.service.notify.facade;

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
    public NotifyMessage save(NotifyMessageVO info) {
        logger.info("NotifyMessageFacade.save.info = [" + info + "]");
        Integer uid = info.getUid();
        String title = info.getTitle();
        String content = info.getContent();
        String uuid = info.getUuid();
        NotifyMessage notifyMessage = new NotifyMessage();
        notifyMessage.setUid(uid);
        notifyMessage.setTitle(title);
        notifyMessage.setContent(content);
        notifyMessage.setUuid(uuid);
        return service.save(notifyMessage);
    }
}
