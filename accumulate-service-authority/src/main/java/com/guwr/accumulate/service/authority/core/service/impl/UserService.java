package com.guwr.accumulate.service.authority.core.service.impl;


import com.guwr.accumulate.facade.authority.entity.User;
import com.guwr.accumulate.facade.authority.exception.AuthorityException;
import com.guwr.accumulate.facade.authority.vo.UserVO;
import com.guwr.accumulate.service.authority.core.dao.UserRepository;
import com.guwr.accumulate.service.authority.core.service.IUserService;
import com.guwr.accumulate.service.authority.core.shiro.token.TokenManager;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.authority.core.service.impl.UserService
 * Date 2016/8/13
 * Time 21:16
 */
@Service
public class UserService implements IUserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository repository;


    @Override
    public User save(User entity) {
        return repository.save(entity);
    }

    @Override
    public User findOne(Integer id) {
        logger.info("UserService.findOne");
        return repository.findOne(id);
    }


    @Override
    public Page<User> pageInfo(UserVO vo) {
        logger.info("UserService.pageInfo");
        Page<User> pageInfo = this.repository.pageInfo(vo);
        return pageInfo;
    }

    @Override
    public User findUserByUsername(String username) {
        logger.info("UserService.findUserByUsername");
        logger.info("username = [" + username + "]");
        List<User> users = repository.findUserByUsername(username);
        if (CollectionUtils.isEmpty(users)) {
            throw AuthorityException.USER_NOT_EXIT.print();
        }
        return users.get(0);
    }

    @Override
    public User login(UserVO vo) {
        User user = TokenManager.login(vo, vo.getRememberMe());
        Session session = TokenManager.getSession();
        session.setAttribute("user", user);
        return user;
    }

//    @Override
//    public User login(UserVO vo) {
//        String username = vo.getUsername();
//        User user = findUserByUsername(username);
//        return user;
//    }


    @Override
    @Transactional
    public User register(UserVO vo) {
        User entity = new User();
        String username = vo.getUsername();
        entity.setUsername(username);
        String password = vo.getPassword();
        SecureRandomNumberGenerator generator = new SecureRandomNumberGenerator();
        String salt = generator.nextBytes().toHex();
        String hex = new Md5Hash(password, salt + username).toHex();
        entity.setPassword(hex);
        entity.setSalt(salt);
        entity = save(entity);
        return entity;
    }

    public static void main(String[] args) {
        SecureRandomNumberGenerator generator = new SecureRandomNumberGenerator();
        System.out.println(generator.nextBytes().toHex());
        String s = new Md5Hash("123", "a7050029d928749ebc8bc647d2bccda6" + "zhangsan").toHex();
        System.out.println("s = " + s);
    }
}
