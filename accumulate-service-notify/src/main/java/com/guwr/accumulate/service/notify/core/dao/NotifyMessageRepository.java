package com.guwr.accumulate.service.notify.core.dao;

import com.guwr.accumulate.common.page.PageParam;
import com.guwr.accumulate.facade.notify.entity.NotifyMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by gwr
 * Description
 * Path com.accumulate.service.notify.core.dao.NotifyMessageRepository
 * Date 2016/8/13
 * Time 21:19
 */
public interface NotifyMessageRepository extends JpaRepository<NotifyMessage, Integer> {

    int listPageCount(String listPageCountStr);

    List<NotifyMessage> listPage(String listPageStr, PageParam pageParam);

    int deleteNotifyMessageByUUID(String uuid);

    int deleteNotifyMessageByUUIDAndQueue(String uuid, String consumerQueue);
}
