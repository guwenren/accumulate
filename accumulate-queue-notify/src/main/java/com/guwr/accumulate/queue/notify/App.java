package com.guwr.accumulate.queue.notify;

import com.guwr.accumulate.common.page.PageBean;
import com.guwr.accumulate.facade.notify.entity.NotifyRecord;
import com.guwr.accumulate.facade.notify.vo.NotifyRecordVO;
import com.guwr.accumulate.queue.notify.core.NotifyPersist;
import com.guwr.accumulate.queue.notify.core.NotifyQueue;
import com.guwr.accumulate.queue.notify.core.NotifyTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.DelayQueue;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.queue.notify.App
 * Date         2016/11/6
 * Time         18:16
 * Description
 */
public class App {
    private static Logger logger = LoggerFactory.getLogger(App.class);

    public static DelayQueue<NotifyTask> tasks = new DelayQueue<>();
    private static ClassPathXmlApplicationContext context;
    private static ThreadPoolTaskExecutor threadPool;

    private static NotifyPersist notifyPersist;
    private static NotifyQueue notifyQueue;

    public static void main(String[] args) {
        try {
            context = new ClassPathXmlApplicationContext(new String[]{"spring/spring-context.xml"});
            context.start();
            threadPool = context.getBean("threadPool", ThreadPoolTaskExecutor.class);
            notifyPersist = context.getBean("notifyPersist", NotifyPersist.class);
            notifyQueue = context.getBean("notifyQueue", NotifyQueue.class);
            startInitFromDB();
            System.out.println("threadPool = " + threadPool);
        } catch (Exception e) {
            logger.error("== application start error:", e);
            return;
        }
        synchronized (App.class) {
            while (true) {
                try {
                    App.class.wait();
                } catch (InterruptedException e) {
                    logger.error("== synchronized error:", e);
                }
            }
        }
    }

    private static void startInitFromDB() {
        logger.info("get data from database");

        int pageNum = 1;
        int numPerPage = 5;

        // 查询状态和通知次数符合以下条件的数据进行通知
//        String[] status = new String[]{"101", "102", "200", "201"};
//        Integer[] notifyTime = new Integer[]{0, 1, 2, 3, 4};
        List<Integer> status = Arrays.asList(101, 102, 200, 201);
        List<Integer> notifyTimes = Arrays.asList(0, 1, 2, 3, 4);

        NotifyRecordVO info = new NotifyRecordVO();
        info.setPageNum(pageNum);
        info.setNumPerPage(numPerPage);
        info.setStatus(status);
        info.setNotifyTimes(notifyTimes);
        PageBean<NotifyRecord> pageBean = notifyPersist.findListPage(info);
        //还有数据
        while (pageNum <= pageBean.getEndPageIndex()) {
            logger.info("pageBean = " + pageBean);
            List<NotifyRecord> recordList = pageBean.getRecordList();
            for (NotifyRecord notifyRecord : recordList) {
                notifyRecord.setUpdateTime(new Date());
                notifyQueue.addElementToList(notifyRecord);
            }
            pageNum++;
            info.setPageNum(pageNum);
            pageBean = notifyPersist.findListPage(info);
        }
    }
}
