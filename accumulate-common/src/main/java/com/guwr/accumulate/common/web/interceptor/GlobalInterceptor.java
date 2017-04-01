package com.guwr.accumulate.common.web.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.common.web.interceptor.GlobalInterceptor
 * Date 2016/8/27
 * Time 19:12
 */
public class GlobalInterceptor implements HandlerInterceptor {

    private static Logger logger = LoggerFactory.getLogger(GlobalInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //        logger.info("GlobalInterceptor.preHandle..1");
        String requestURI = httpServletRequest.getRequestURI();
        // 找到运行的Action对象，并打印其类名
        logger.info("requestURI：" + requestURI);
        // 找到运行的ActionProxy对象，并打印其要运行的方法名
        String method = httpServletRequest.getMethod();
        logger.info("method：" + method);
        // 找到这次请求的request中的parameter参数，并打印
        Map params = httpServletRequest.getParameterMap();
//        logger.info("MethodType：" + request.getMethod());
//        if (!CollectionUtils.isEmpty(params)) {
//            logger.info("params：");
//        }
        for (Object key : params.keySet()) {
            Object obj = params.get(key);
            if (obj instanceof String[]) {
                logger.info(key + "：" + StringUtils.join((String[]) obj, ","));
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
