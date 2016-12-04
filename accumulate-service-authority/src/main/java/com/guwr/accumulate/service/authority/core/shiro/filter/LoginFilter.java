package com.guwr.accumulate.service.authority.core.shiro.filter;

import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.authority.core.shiro.filter.LoginFilter
 * Date 2016/9/7
 * Time 11:57
 */
public class LoginFilter extends AccessControlFilter {

    private static Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        logger.info("LoginFilter.isAccessAllowed");
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        logger.info("LoginFilter.onAccessDenied");
        return false;
    }
}
