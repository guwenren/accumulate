package com.guwr.accumulate.facade.authority.facade;

import com.guwr.accumulate.facade.authority.entity.User;
import com.guwr.accumulate.facade.authority.vo.UserVO;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.notify.facade.IUserFacade
 * Date 2016/8/21
 * Time 18:33
 */
public interface IUserFacade {
    User login(UserVO vo);

    User findUserByUsername(String username);
}
