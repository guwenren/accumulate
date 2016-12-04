package com.guwr.accumulate.service.authority.interceptor;

import com.guwr.accumulate.facade.authority.entity.User;
import com.guwr.accumulate.service.authority.core.shiro.token.TokenManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.authority.interceptor.LoginInterceptor
 * Date 2016/9/7
 * Time 16:11
 */
public class LoginInterceptor implements HandlerInterceptor {

    private static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        logger.info("LoginInterceptor.preHandle");
        User user = TokenManager.getToken();
        logger.info("user = " + user);
        if (user == null) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        logger.info("LoginInterceptor.postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        logger.info("LoginInterceptor.afterCompletion");
    }
}
