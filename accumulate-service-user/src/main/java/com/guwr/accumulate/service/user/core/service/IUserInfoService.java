package com.guwr.accumulate.service.user.core.service;


import com.guwr.accumulate.common.page.PageBean;
import com.guwr.accumulate.facade.user.entity.UserInfo;
import com.guwr.accumulate.facade.user.vo.UserInfoVO;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.wmps.service.IUserInfoService
 * Date 2016/8/13
 * Time 21:07
 */
public interface IUserInfoService {
    UserInfo save(UserInfo entity);

    UserInfo edit(UserInfo entity);

    UserInfo findOne(Integer id);

    UserInfo register(UserInfoVO vo);

    UserInfo findOneByMobile(String mobile);

    PageBean<UserInfo> findListPage(UserInfoVO info);
}
