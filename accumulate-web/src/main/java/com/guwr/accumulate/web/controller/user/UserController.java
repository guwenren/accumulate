package com.guwr.accumulate.web.controller.user;

import com.guwr.accumulate.common.page.PageBean;
import com.guwr.accumulate.common.web.controller.BaseController;
import com.guwr.accumulate.facade.user.entity.UserInfo;
import com.guwr.accumulate.facade.user.facade.IUserInfoFacade;
import com.guwr.accumulate.facade.user.vo.UserInfoVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.controller.user.UserController
 * Date         2016/11/10
 * Time         11:32
 * Description
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserInfoFacade userInfoFacade;

    /**
     * 列出用户信息.
     *
     * @return
     */
    @RequestMapping("list")
    @RequiresPermissions("user:list")
    public String list(Model model, UserInfoVO info) {
        PageBean<UserInfo> listPage = userInfoFacade.findListPage(info);
        model.addAttribute(MODEL_KEY, listPage);
        model.addAttribute(QUERY_KEY, info);
        return "user/user_list";
    }

    /**
     * 查看用户详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "view/{id}")
    public String view(@PathVariable Integer id, Model model) {
        UserInfo userInfo = userInfoFacade.findOne(id);
        model.addAttribute("m", userInfo);
        return "user/user_view";
    }


    @RequestMapping(value = "add", method = {RequestMethod.GET})
    public String add() {
        return "user/user_add";
    }

    /**
     * 保存一个用户
     */
    @RequestMapping(value = "add", method = {RequestMethod.POST})
    public String add(UserInfoVO info, Model model) {
        String msg = validate(info);
        if (StringUtils.isNotBlank(msg)) {
            return operateError(msg, model);
        }
        UserInfo userInfo = userInfoFacade.save(info);
        if (userInfo == null) {
            logger.info("添加失败");
        }
        return operateSuccess(model);
    }

    @RequestMapping(value = "edit/{id}", method = {RequestMethod.GET})
    public String edit(@PathVariable Integer id, Model model) {
        logger.info("UserController.edit");
        UserInfo userInfo = userInfoFacade.findOne(id);
        model.addAttribute(MODEL_KEY, userInfo);
        return "user/user_edit";
    }

    @RequestMapping(value = "edit", method = {RequestMethod.POST})
    public String edit(UserInfoVO info, Model model) {
        logger.info("UserController.edit");
        userInfoFacade.edit(info);
        return operateSuccess(model);
    }

    public String validate(UserInfoVO info) {
        StringBuilder msgBuilder = new StringBuilder("");
        if (StringUtils.isBlank(info.getMobile())) {
            msgBuilder.append("手机号码不能为空");
        }
        return msgBuilder.toString();
    }

    public String test() {
        return "test";
    }
}
