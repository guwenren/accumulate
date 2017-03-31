package com.guwr.accumulate.service.authority.core.service;


import com.guwr.accumulate.facade.authority.entity.User;
import com.guwr.accumulate.facade.authority.vo.UserVO;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.authority.core.service.IUserService
 * Date 2016/8/13
 * Time 21:07
 */
public interface IUserService {
    /**
     *
     * @param entity
     * @return
     */
    User save(User entity);

    /**
     *
     * @param id
     * @return
     */
    User findOne(Integer id);

    Page<User> pageInfo(UserVO info);

    User findUserByUsername(String username);

    User login(UserVO vo);

//    User login(UserVO vo);

    User register(UserVO vo);
}
