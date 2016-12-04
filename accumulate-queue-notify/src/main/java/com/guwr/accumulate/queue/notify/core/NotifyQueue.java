package com.guwr.accumulate.queue.notify.core;

import com.guwr.accumulate.facade.notify.entity.NotifyRecord;
import com.guwr.accumulate.facade.notify.enums.NotifyRecordEnum;
import com.guwr.accumulate.queue.notify.App;
import com.guwr.accumulate.queue.notify.entity.NotifyParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.queue.notify.core.NotifyQueue
 * Date         2016/11/6
 * Time         15:47
 * Description
 */
@Component
public class NotifyQueue implements Serializable {

    private static final long serialVersionUID = 755182121113898389L;

    private static Logger logger = LoggerFactory.getLogger(NotifyPersist.class);
    @Autowired
    private NotifyParam notifyParam;
    @Autowired
    private NotifyPersist notifyPersist;

    public void addElementToList(NotifyRecord notifyRecord) {
        if (notifyRecord == null) {
            return;
        }
        Integer notifyTimes = notifyRecord.getNotifyTimes(); // 通知次数
        Integer maxNotifyTime = 0;
        try {
            maxNotifyTime = notifyParam.getMaxNotifyTime(); // 最大通知次数
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        if (Objects.equals(notifyRecord.getVersion(), 0)) {// 刚刚接收到的数据
            notifyRecord.setUpdateTime(new Date());
        }
        long time = notifyRecord.getUpdateTime().getTime();//最后通知时间
        Map<Integer, Integer> timeMap = notifyParam.getNotifyParams();

        if (notifyTimes < maxNotifyTime) {
            Integer nextKey = notifyTimes + 1; // 当前次数
            Integer next = timeMap.get(nextKey); // 当前次数对应的通知间隔时间
            if (next != null) {
                time += 1000 * 60 * next + 1; // 当前次数对应的秒数
                notifyRecord.setUpdateTime(new Date(time));
                NotifyTask notifyTask = new NotifyTask(notifyRecord, this, notifyParam);
                App.tasks.add(notifyTask);
            }
        } else {
            try {
                Integer id = notifyRecord.getId();
                int value = NotifyRecordEnum.NotifyRecordEnumStatus.FAILED.getValue();
                notifyPersist.updateNotifyRord(id, notifyTimes, value);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }
        }
    }
}
