package com.guwr.accumulate.service.authority.controller;

import com.guwr.accumulate.common.util.ResultData;
import com.guwr.accumulate.facade.authority.entity.User;
import com.guwr.accumulate.facade.authority.vo.UserVO;
import com.guwr.accumulate.service.authority.core.service.IUserService;
import com.guwr.accumulate.service.authority.core.shiro.token.TokenManager;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.authority.controller.IndexController
 * Date 2016/8/28
 * Time 7:51
 */
@Controller
public class IndexController {

    private static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private IUserService userService;

    @RequestMapping("index")
    public String index() {
        logger.info("IndexController.index");
        return "index";
    }

    @RequestMapping(value = "logout")
    public String logout() {
        logger.info("LoginController.logout");
        TokenManager.logout();
        return "logout";
    }
}
