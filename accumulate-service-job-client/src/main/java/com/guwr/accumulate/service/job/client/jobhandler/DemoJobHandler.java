package com.guwr.accumulate.service.job.client.jobhandler;

import com.guwr.accumulate.facade.job.handler.IJobHandler;
import com.guwr.accumulate.facade.job.handler.annotation.JobHander;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.job.client.jobhandler.DemoJobHandler
 * Date 2016/9/27
 * Time 13:55
 */
@JobHander(value = "demoJobHandler")
@Component
public class DemoJobHandler extends IJobHandler {
    private static Logger logger = LoggerFactory.getLogger(DemoJobHandler.class);

    @Override
    public void execute(String... params) throws Exception {
        logger.info("DemoJobHandler.execute");
    }
}
