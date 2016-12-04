package com.guwr.accumulate.service.job.util;

import com.guwr.accumulate.facade.job.entity.QrtzTriggerInfo;
import com.guwr.accumulate.facade.job.executor.JobExecutor;
import com.guwr.accumulate.service.job.BaseTest;
import com.guwr.accumulate.service.job.callback.JobLogCallbackServerHandler;
import com.guwr.accumulate.service.job.jobbean.RemoteHttpJobBean;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.util.thread.ExecutorThreadPool;
import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.job.util.DynamicSchedulerUtilsTest
 * Date 2016/9/18
 * Time 15:53
 */
public class DynamicSchedulerUtilsTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(DynamicSchedulerUtilsTest.class);
//    @Autowired
//    private DynamicSchedulerUtils dynamicSchedulerUtils;

    //    @Autowired
//    Scheduler scheduler;
    @Test
    public void test1() throws Exception {
        int port = 8888;
        Server server = null;
        server = new Server();
        server.setThreadPool(new ExecutorThreadPool(200, 200, 30000));    // 非阻塞

        // connector
        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setPort(port);
        connector.setMaxIdleTime(30000);
        server.setConnectors(new Connector[]{connector});

        // handler
        HandlerCollection handlerc = new HandlerCollection();
        handlerc.setHandlers(new Handler[]{new JobLogCallbackServerHandler()});

        server.setHandler(handlerc);

        try {
            server.start();
            System.out.println("start");
            server.join();  // block until server ready
            System.out.println("join");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.in.read();
    }

    @Test
    public void addJob() throws Exception {
        logger.info("DynamicSchedulerUtilsTest.addJob");
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        QrtzTriggerInfo jobInfo = new QrtzTriggerInfo();
        jobInfo.setJobName("201609141528030875");
        jobInfo.setJobGroup("defaults");
        jobInfo.setJobCron("0/30 * * * * ?");
        // TriggerKey : name + group
        TriggerKey triggerKey = TriggerKey.triggerKey(jobInfo.getJobName(), jobInfo.getJobGroup());
        JobKey jobKey = new JobKey(jobInfo.getJobName(), jobInfo.getJobGroup());
        // TriggerKey valid if_exists
        // CronTrigger : TriggerKey + cronExpression	// withMisfireHandlingInstructionDoNothing 忽略掉调度终止过程中忽略的调度
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(jobInfo.getJobCron()).withMisfireHandlingInstructionDoNothing();
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(cronScheduleBuilder).build();
        // JobDetail : jobClass
        Class<? extends Job> jobClass_ = RemoteHttpJobBean.class;   // Class.forName(jobInfo.getJobClass());

        JobDetail jobDetail = JobBuilder.newJob(jobClass_).withIdentity(jobKey).build();
        // schedule : jobDetail + cronTrigger
        Date date = new Date();
        if (scheduler.checkExists(triggerKey)) {
            scheduler.resumeJob(jobKey);
        } else {
            date = scheduler.scheduleJob(jobDetail, cronTrigger);
        }
//          scheduler.resumeJob(jobKey);
//        scheduler.start();
        logger.info(">>>>>>>>>>> addJob success, jobDetail:{}, cronTrigger:{}, date:{}", jobDetail, cronTrigger, date);
        System.in.read();
    }
    @Test
    public void deleteJob() throws Exception {
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        JobKey jobKey = new JobKey("defaults", "201609141528030875");
        scheduler.deleteJob(jobKey);
    }

    /**
     * 从数据库中找到已经存在的job，并重新开户调度
     */
    @Test
    public void resumeJob() {
        logger.info("DynamicSchedulerUtilsTest.resumeJob");
        try {
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();
            // ①获取调度器中所有的触发器组
            List<String> triggerGroups = scheduler.getTriggerGroupNames();
            // ②重新恢复在tgroup1组中，名为trigger1_1触发器的运行
            for (int i = 0; i < triggerGroups.size(); i++) {
                List<String> triggers = scheduler.getTriggerGroupNames();
                for (int j = 0; j < triggers.size(); j++) {
                    Trigger tg = scheduler.getTrigger(new TriggerKey(triggers
                            .get(j), triggerGroups.get(i)));
                    // ②-1:根据名称判断
                    if (tg instanceof SimpleTrigger
                            && tg.getDescription().equals("tgroup1.trigger1_1")) {
                        // ②-1:恢复运行
                        scheduler.resumeJob(new JobKey(triggers.get(j),
                                triggerGroups.get(i)));
                    }
                }

            }
            scheduler.start();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}