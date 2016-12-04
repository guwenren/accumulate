package com.guwr.accumulate.controller.user;

import com.guwr.accumulate.common.web.controller.BaseController;
import com.guwr.accumulate.facade.user.entity.UserInfo;
import com.guwr.accumulate.facade.user.facade.IUserInfoFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.controller.user.LoginController
 * Date         2016/11/10
 * Time         11:32
 * Description
 */
@Controller
public class LoginController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private IUserInfoFacade userInfoFacade;

    /**
     * 进入登录页面.
     *
     * @return
     */
    @RequestMapping("/")
    public String index() {
        logger.info("LoginController.index");
        return "login";
    }

    @RequestMapping(value = "login", method = {RequestMethod.GET})
    public String login() {
        logger.info("LoginController.login");
        return "login";
    }

    /**
     * 登录验证
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "login", method = {RequestMethod.POST})
    public String login(Model model, String mobile, String password) {
        logger.info("LoginController.login");
        logger.info("mobile = [" + mobile + "], password = [" + password + "]");
        UserInfo userInfo = userInfoFacade.findOneByMobile(mobile);
        model.addAttribute(MODEL_KEY, userInfo);
        return "index";
    }
}
