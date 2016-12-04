package com.guwr.accumulate.service.authority.core.shiro.token;

import com.guwr.accumulate.facade.authority.entity.User;
import com.guwr.accumulate.facade.authority.vo.UserVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.authority.core.shiro.token.TokenManager
 * Date 2016/9/6
 * Time 18:00
 */
public class TokenManager implements Serializable {

    private static final long serialVersionUID = -187964166510345032L;

    private static Logger logger = LoggerFactory.getLogger(TokenManager.class);

    /**
     * @param vo
     * @param rememberMe
     * @return
     */
    public static User login(UserVO vo, boolean rememberMe) {
        logger.info("TokenManager.login");
        UsernamePasswordToken token = new UsernamePasswordToken(vo.getUsername(), vo.getPassword());
        token.setRememberMe(rememberMe);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        return getToken();
    }

    /**
     * @return
     */
    public static User getToken() {
        logger.info("TokenManager.getToken");
        Subject subject = SecurityUtils.getSubject();
        User token = (User) subject.getPrincipal();
        return token;
    }

    public static Session getSession() {
        logger.info("TokenManager.getSession");
        Subject subject = SecurityUtils.getSubject();
        return subject.getSession();
    }

    public static void logout() {
        logger.info("TokenManager.logout");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }
}
