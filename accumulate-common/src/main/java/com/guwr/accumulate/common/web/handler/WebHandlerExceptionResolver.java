package com.guwr.accumulate.common.web.handler;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.guwr.accumulate.common.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.common.web.handler.WebHandlerExceptionResolver
 * Date 2016/9/6
 * Time 9:39
 */
@Component
public class WebHandlerExceptionResolver implements HandlerExceptionResolver {


    private static Logger logger = LoggerFactory.getLogger(WebHandlerExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView mv = new ModelAndView();
            /*  使用FastJson提供的FastJsonJsonView视图返回，不需要捕获异常   */
        e.printStackTrace();
        FastJsonJsonView view = new FastJsonJsonView();
        String message = e.getMessage();
        Map<String, Object> attributes = new HashMap<>();
        int code = 00000; //系统异常
        if (e instanceof BaseException) {
            BaseException baseException = (BaseException) e;
            message = baseException.getMessage();
            code = baseException.getCode();
        }
        attributes.put("data", new Object[]{});
        attributes.put("message", message);
        attributes.put("code", code);//result.setCode(1);
        view.setAttributesMap(attributes);
        mv.setView(view);
        logger.info("异常:" + message, e);
        return mv;
    }
}
