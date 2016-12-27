package com.guwr.accumulate.service.notify.core.dao;

import com.guwr.accumulate.common.page.PageParam;
import com.guwr.accumulate.facade.notify.entity.NotifyTransactionMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by gwr
 * Description
 * Path com.accumulate.service.notify.core.dao.NotifyTransactionMessageRepository
 * Date 2016/8/13
 * Time 21:19
 */
public interface NotifyTransactionMessageRepository extends JpaRepository<NotifyTransactionMessage, Integer> {

    int listPageCount(String listPageCountStr);

    List<NotifyTransactionMessage> listPage(String listPageStr, PageParam pageParam);

    int deleteNotifyTransactionMessageByUUID(String uuid);

    int deleteNotifyTransactionMessageByUUIDAndQueue(String uuid, String consumerQueue);
}
