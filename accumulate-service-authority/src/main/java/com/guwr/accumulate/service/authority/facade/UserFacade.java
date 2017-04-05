package com.guwr.accumulate.service.authority.facade;

import com.guwr.accumulate.facade.authority.entity.User;
import com.guwr.accumulate.facade.authority.exception.AuthorityException;
import com.guwr.accumulate.facade.authority.facade.IUserFacade;
import com.guwr.accumulate.facade.authority.vo.UserVO;
import com.guwr.accumulate.service.authority.core.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by guwr
 * Project_name accumulate
 * Path com.guwr.accumulate.service.authority.facade.UserFacade
 * Date 2017/4/5
 * Time 14:26
 * Description
 */
@Component
public class UserFacade implements IUserFacade {

    @Autowired
    private IUserService userService;

    @Override
    public User login(UserVO vo) {
        return userService.login(vo);
    }

    @Override
    public User findUserByUsername(String username) {
        List<User> users = userService.findUserByUsername(username);

        if (CollectionUtils.isEmpty(users)) {
            throw AuthorityException.USER_NOT_EXIT.print();
        }

        return users.get(0);
    }
}
