package com.guwr.accumulate.facade.job.router.thread;

import com.guwr.accumulate.facade.job.router.model.RequestModel;
import com.guwr.accumulate.facade.job.router.model.ResponseModel;
import com.guwr.accumulate.facade.job.util.RequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.job.router.thread.TriggerCallbackThread
 * Date 2016/9/27
 * Time 13:22
 */
public class TriggerCallbackThread {
    private static Logger logger = LoggerFactory.getLogger(TriggerCallbackThread.class);
    /**
     *
     */
    private static LinkedBlockingQueue<RequestModel> callBackQueue = new LinkedBlockingQueue<>();

    static {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        RequestModel requestModel = callBackQueue.take();
                        if (requestModel != null) {
                            try {
                                final String logAddress = requestModel.getLogAddress();
                                String reqURL = "http://127.0.0.1:" + logAddress + "/";
                                logger.info("调用服务端开始{}", System.currentTimeMillis());
                                ResponseModel responseModel = RequestUtils.postHex(reqURL, requestModel);
                                logger.info("调用服务端结束{}", System.currentTimeMillis());
                                logger.info(">>>>>>>>>>> job callback , RequestModel:{}, ResponseModel:{}", new Object[]{requestModel.toString(), responseModel.toString()});
                            } catch (Exception e) {
                                logger.info("JobThread Exception:", e);
                            }
                        }
                    } catch (Exception e) {
                        logger.error("", e);
                    }
                }
            }
        }).start();
    }

    public static void pushCallBack(RequestModel callback) {
        logger.info("TriggerCallbackThread.pushCallBack");
        callBackQueue.add(callback);
        logger.debug(">>>>>>>>>>> job, push callback request, logId:{}", callback.getLogId());
    }
}
