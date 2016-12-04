package com.guwr.accumulate.common.web.interceptor;

import com.guwr.accumulate.common.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

import java.util.Map;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.common.web.interceptor.GlobalInterceptor
 * Date 2016/8/27
 * Time 19:12
 */
public class GlobalInterceptor implements WebRequestInterceptor {

    private static Logger logger = LoggerFactory.getLogger(GlobalInterceptor.class);

    @Override
    public void preHandle(WebRequest request) throws Exception {
        printReqLog(request);
        logger.info("1");
    }

    @Override
    public void postHandle(WebRequest request, ModelMap model) throws Exception {
        logger.info("2");
    }

    @Override
    public void afterCompletion(WebRequest request, Exception ex) throws Exception {
        logger.info("3");
    }

    /**
     * 打印访问参数
     *
     * @param request
     */
    private void printReqLog(WebRequest request) {
        Map<String, String[]> params = request.getParameterMap();
        if (CollectionUtils.isEmpty(params)) {
            return;
        }
        CommonUtils.printObj2Json(params, logger);
    }
}
