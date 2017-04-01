package com.guwr.accumulate.service.user.facade;

import com.guwr.accumulate.common.page.PageBean;
import com.guwr.accumulate.facade.user.entity.UserInfo;
import com.guwr.accumulate.facade.user.exception.UserException;
import com.guwr.accumulate.facade.user.facade.IUserInfoFacade;
import com.guwr.accumulate.facade.user.vo.UserInfoVO;
import com.guwr.accumulate.service.user.core.service.IUserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.user.facade.UserInfoFacade
 * Date 2016/8/21
 * Time 18:31
 */
@Component
public class UserInfoFacade implements IUserInfoFacade {

    private static Logger logger = LoggerFactory.getLogger(UserInfoFacade.class);
    @Autowired
    private IUserInfoService userInfoService;

    @Override
    public UserInfo findOne(int id) {
        logger.info("id =[" + id + "]");
        UserInfo userInfo = userInfoService.findOne(id);
        return userInfo;
    }

    @Override
    public UserInfo findOneCheck(int id) {
        logger.info("id =[" + id + "]");
        UserInfo userInfo = this.findOne(id);
        if (userInfo == null) { //用户
            throw UserException.YONG_HU_BU_CUN_ZAI.print();
        }
        return userInfo;
    }

    @Override
    public UserInfo findOneByMobile(String mobile) {
        logger.info("findOneByMobile.mobile = [" + mobile + "]");
        return userInfoService.findOneByMobile(mobile);
    }

    @Override
    public PageBean<UserInfo> findListPage(UserInfoVO info) {
        logger.info("findListPage.info = [" + info + "]");
        return userInfoService.findListPage(info);
    }

    @Override
    public UserInfo save(UserInfoVO info) {
        logger.info("info = [" + info + "]");
        UserInfo entity = new UserInfo();
        entity.setMobile(info.getMobile());
        entity.setPassword(info.getPassword());
        entity.setRealname(info.getRealname());
        return userInfoService.save(entity);
    }

    @Override
    public UserInfo edit(UserInfoVO info) {
        logger.info("info = " + info);
        UserInfo entity = userInfoService.findOne(info.getId());
        entity.setMobile(info.getMobile());
        entity.setPassword(info.getPassword());
        entity.setRealname(info.getRealname());
        return userInfoService.edit(entity);
    }
}
