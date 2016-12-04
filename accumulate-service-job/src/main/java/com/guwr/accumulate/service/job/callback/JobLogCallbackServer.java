package com.guwr.accumulate.service.job.callback;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.util.thread.ExecutorThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by gwr
 * Description   异步通知服务
 * Path com.guwr.accumulate.service.job.callback.JobLogCallbackServer
 * Date 2016/9/25
 * Time 21:41
 */
public class JobLogCallbackServer {
    private static final Logger logger = LoggerFactory.getLogger(JobLogCallbackServer.class);

    private static String  trigger_log_address;

    public static String  getTrigger_log_address() {
        return trigger_log_address;
    }

    private Server server = null;

    public void start(final int callBackPort) throws Exception {
        logger.info("JobLogCallbackServer.start.41");
        //       init address
        //       String ip = IpUtil.getIp();
        //       trigger_log_address = ip.concat(":").concat(String.valueOf(callBackPort));
        trigger_log_address = String.valueOf(callBackPort);
        new Thread(new Runnable() {
            @Override
            public void run() {
                server = new Server();
                server.setThreadPool(new ExecutorThreadPool(200, 200, 30000));    // 非阻塞

                // connector
                SelectChannelConnector connector = new SelectChannelConnector();
                connector.setPort(callBackPort);
                connector.setMaxIdleTime(30000);
                server.setConnectors(new Connector[]{connector});

                // handler
                HandlerCollection handlerc = new HandlerCollection();
                handlerc.setHandlers(new Handler[]{new JobLogCallbackServerHandler()});
                server.setHandler(handlerc);

                try {
                    server.start();
                    server.join();  // block until server ready
                    logger.info(">>>>>>>>>>jetty服务{}端口启动了", callBackPort);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void destroy() {
        logger.info("JobLogCallbackServer.destroy");
        if (server != null) {
            try {
                server.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
