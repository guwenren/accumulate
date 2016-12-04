package com.guwr.accumulate.service.authority.core.dao;

import com.guwr.accumulate.facade.authority.entity.User;
import com.guwr.accumulate.facade.authority.vo.UserVO;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by gwr
 * Description
 * Path com.accumulate.service.notify.core.dao.UserRepository
 * Date 2016/8/13
 * Time 21:19
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * @param username
     * @return
     */
    List<User> findUserByUsername(String username);

    /**
     * @param vo
     * @return
     */
    Page<User> pageInfo(UserVO vo);
}
