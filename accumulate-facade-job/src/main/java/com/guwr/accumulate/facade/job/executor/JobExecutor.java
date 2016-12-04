package com.guwr.accumulate.facade.job.executor;

import com.guwr.accumulate.facade.job.handler.IJobHandler;
import com.guwr.accumulate.facade.job.handler.annotation.JobHander;
import com.guwr.accumulate.facade.job.router.HandlerRouter;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.util.thread.ExecutorThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.CollectionUtils;
import java.util.Map;

/**
 * Created by gwr
 * Description  客户端请求服务器，并加载job任务类
 * Path com.guwr.accumulate.facade.job.executor.JobExecutor
 * Date 2016/9/27
 * Time 10:30
 */
public class JobExecutor implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(JobExecutor.class);

    //端口默认9999
    private int port = 9999;

    public void setPort(int port) {
        this.port = port;
    }

    //服务
    private Server server = null;


    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info("JobExecutor.setApplicationContext");
        JobExecutor.applicationContext = applicationContext;
        initJobHandler();
    }

    private void initJobHandler() {
        logger.info("JobExecutor.initJobHandler");
        Map<String, Object> beansWithAnnotation = JobExecutor.applicationContext.getBeansWithAnnotation(JobHander.class);
        if (CollectionUtils.isEmpty(beansWithAnnotation)) {
            logger.info(" JobHander is null");
            return;
        }
        for (Object o : beansWithAnnotation.values()) {
            if (o instanceof IJobHandler) {
                String name = o.getClass().getAnnotation(JobHander.class).value();
                IJobHandler handler = (IJobHandler) o;
                HandlerRouter.registJobHandler(name, handler);
            }
        }
    }
        //启动

    public void start() {
        logger.info("JobExecutor.start");
        new Thread(new Runnable() {
            @Override
            public void run() {
                logger.info("JobExecutor.run");
                server = new Server();
                server.setThreadPool(new ExecutorThreadPool(200, 200, 30000));    // 非阻塞
                // connector
                SelectChannelConnector connector = new SelectChannelConnector();
                connector.setPort(port);
                connector.setMaxIdleTime(30000);
                server.setConnectors(new Connector[]{connector});

                // handler
                HandlerCollection handler = new HandlerCollection();
                handler.setHandlers(new Handler[]{new JobExecutorHandler()});
                server.setHandler(handler);
                try {
                    server.start();
                    server.join();
                    logger.info(">>>>>>>>>>jetty.{}.start", port);
                } catch (Exception e) {
                    logger.info(">>>>>>>>>>jetty.{}.fail", port);
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //销毁
    public void destroy() {
        logger.info("JobExecutor.destroy");
        if (server == null) {
            logger.info("server is null");
            return;
        }
        try {
            server.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
