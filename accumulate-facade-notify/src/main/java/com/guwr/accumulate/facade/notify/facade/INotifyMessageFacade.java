package com.guwr.accumulate.facade.notify.facade;

import com.guwr.accumulate.facade.notify.entity.NotifyMessage;
import com.guwr.accumulate.facade.notify.vo.NotifyMessageVO;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.notify.facade.INotifyMessageFacade
 * Date 2016/8/21
 * Time 18:33
 */
public interface INotifyMessageFacade {
    NotifyMessage save(NotifyMessageVO vo);
}
