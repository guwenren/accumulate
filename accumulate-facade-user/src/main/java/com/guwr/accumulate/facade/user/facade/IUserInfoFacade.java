package com.guwr.accumulate.facade.user.facade;


import com.guwr.accumulate.common.page.PageBean;
import com.guwr.accumulate.facade.user.entity.UserInfo;
import com.guwr.accumulate.facade.user.vo.UserInfoVO;

import java.io.Serializable;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.user.facade.IUserInfoFacade
 * Date 2016/8/21
 * Time 18:33
 */
public interface IUserInfoFacade extends Serializable{
    /**
     * @param id
     * @return
     */
    UserInfo findOne(int id);
    /**
     * @param id
     * @return
     */
    UserInfo findOneCheck(int id);

    /**
     * @param mobile
     * @return
     */
    UserInfo findOneByMobile(String mobile);

    /**
     * @param info
     * @return
     */
    PageBean<UserInfo> findListPage(UserInfoVO info);

    /**
     *
     * @param info
     * @return
     */
    UserInfo save(UserInfoVO info);

    /**
     *
     * @param info
     * @return
     */
    UserInfo edit(UserInfoVO info);
}
