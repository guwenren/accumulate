package com.guwr.accumulate.common.web.handler;

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

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        Map<String, Object> model = new HashMap<>();
        model.put("e", e);
        System.out.println("model = " + model);
        return null;
    }
}
