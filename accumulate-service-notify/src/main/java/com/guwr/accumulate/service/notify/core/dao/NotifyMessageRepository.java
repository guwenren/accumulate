package com.guwr.accumulate.service.notify.core.dao;
import com.guwr.accumulate.facade.notify.entity.NotifyMessage;
import com.guwr.accumulate.facade.notify.entity.NotifyRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by gwr
 * Description
 * Path com.accumulate.service.notify.core.dao.NotifyMessageRepository
 * Date 2016/8/13
 * Time 21:19
 */
public interface NotifyMessageRepository extends JpaRepository<NotifyMessage, Integer> {

}
