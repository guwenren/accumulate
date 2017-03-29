package com.guwr.accumulate.service.user.facade;

import com.guwr.accumulate.common.page.PageBean;
import com.guwr.accumulate.facade.user.entity.UserInfo;
import com.guwr.accumulate.facade.user.exception.UserBizException;
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

    @Override
    public UserInfo findOne(int id) {
        return null;
    }

    @Override
    public UserInfo findOneCheck(int id) {
        return null;
    }

    @Override
    public UserInfo findOneByMobile(String mobile) {
        return null;
    }

    @Override
    public PageBean<UserInfo> findListPage(UserInfoVO info) {
        return null;
    }

    @Override
    public UserInfo save(UserInfoVO info) {
        return null;
    }

    @Override
    public UserInfo edit(UserInfoVO info) {
        return null;
    }
}
