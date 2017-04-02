package com.guwr.accumulate.service.authority.controller;

import com.guwr.accumulate.facade.authority.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.authority.controller.LoginController
 * Date 2016/8/28
 * Time 7:51
 */
@Controller
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "login")
    public String login() {
        logger.info("login");
        return "login";
    }


    @RequestMapping(value = "doLogin")
    @ResponseBody
    public String doLogin(UserVO vo) {
        logger.info("IndexController.login POST");
        return "success";
    }
}
