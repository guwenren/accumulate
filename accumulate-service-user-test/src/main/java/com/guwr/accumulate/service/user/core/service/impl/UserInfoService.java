package com.guwr.accumulate.service.user.core.service.impl;


import com.guwr.accumulate.common.page.PageBean;
import com.guwr.accumulate.facade.user.entity.UserInfo;
import com.guwr.accumulate.facade.user.vo.UserInfoVO;
import com.guwr.accumulate.service.user.core.service.IUserInfoService;
import org.springframework.stereotype.Service;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.user.service.impl.UserInfoService
 * Date 2016/8/13
 * Time 21:16
 */
@Service
public class UserInfoService implements IUserInfoService {

    @Override
    public UserInfo save(UserInfo entity) {
        return null;
    }

    @Override
    public UserInfo edit(UserInfo entity) {
        return null;
    }

    @Override
    public UserInfo findOne(Integer id) {
        return null;
    }

    @Override
    public UserInfo register(UserInfoVO vo) {
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
}
