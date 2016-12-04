package com.guwr.accumulate.service.authority.controller;

import com.guwr.accumulate.common.web.vo.DataVO;
import com.guwr.accumulate.facade.authority.entity.User;
import com.guwr.accumulate.facade.authority.vo.UserVO;
import com.guwr.accumulate.service.authority.core.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.authority.controller.IndexController
 * Date 2016/8/28
 * Time 7:51
 */
@Controller
@RequestMapping("authority")
public class AuthorityController {

    private static Logger logger = LoggerFactory.getLogger(AuthorityController.class);

    @Autowired
    private IUserService userService;


    @RequestMapping("index")
    public String index() {
        logger.info("AuthorityController.index");
        return "authority/index";
    }

    @RequestMapping("list")
    @ResponseBody
    public DataVO<User> list(UserVO vo) {
        logger.info("AuthorityController.list");
        Page<User> userPage = userService.pageInfo(vo);
        DataVO<User> dataVO = new DataVO<>();
        dataVO.setDraw(1);
        dataVO.setRecordsFiltered(userPage.getNumberOfElements());
        dataVO.setRecordsTotal(userPage.getSize());
        dataVO.setData(userPage.getContent());
        return dataVO;
    }
}
