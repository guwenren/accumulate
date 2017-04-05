package com.guwr.accumulate.web.controller;

import com.guwr.accumulate.common.web.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.controller.notify.IndexController
 * Date         2016/11/10
 * Time         11:32
 * Description
 */
@Controller
public class IndexController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(IndexController.class);


    /**
     * 列出用户信息.
     *
     * @return
     */
    @RequestMapping("/")
    public String index() {
        logger.info("index");
        return "redirect:/index.jsp";
    }
}
