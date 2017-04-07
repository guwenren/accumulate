package com.guwr.accumulate.web.controller.user;

import com.guwr.accumulate.common.web.controller.BaseController;
import com.guwr.accumulate.facade.authority.entity.User;
import com.guwr.accumulate.facade.authority.facade.IUserFacade;
import com.guwr.accumulate.facade.authority.vo.UserVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.controller.user.LoginController
 * Date         2016/11/10
 * Time         11:32
 * Description
 */
@Controller
public class LoginController extends BaseController implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private IUserFacade userFacade;

    @RequestMapping(value = "login", method = {RequestMethod.GET})
    public String login() {
        logger.info("login");
        return "login";
    }

    /**
     * 登录验证
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "login", method = {RequestMethod.POST})
    public String login(HttpServletRequest request, UserVO vo) {
        String username = vo.getUsername();
        String password = vo.getPassword();
        User user = userFacade.login(vo);
        Subject subject = SecurityUtils.getSubject();
        boolean authenticated = subject.isAuthenticated();
        if (!authenticated) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
            if (subject.isPermitted("permission:list")) {
                logger.info("yes");
            }
        }
        request.getSession().setAttribute("user", user);
        return "index";
    }
}
