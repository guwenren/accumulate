package com.guwr.accumulate.service.authority.core.service.impl;


import com.guwr.accumulate.common.util.CommonUtils;
import com.guwr.accumulate.facade.authority.entity.User;
import com.guwr.accumulate.facade.authority.vo.UserVO;
import com.guwr.accumulate.service.authority.BaseTest;
import com.guwr.accumulate.service.authority.core.service.IUserService;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.mgt.SecurityManager;
//import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.authority.core.service.impl.UserServiceTest
 * Date 2016/8/28
 * Time 20:55
 */
public class UserServiceTest extends BaseTest {



    private static Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    @Autowired
    private IUserService userService;

//    @Autowired
//    private SecurityManager securityManager;

    @Test
    public void save() throws Exception {

    }

    @Test
    public void findOne() throws Exception {
//        SecurityUtils.setSecurityManager(securityManager);
//        SecurityManager securityManager = SecurityUtils.getSecurityManager();
//        Subject subject = SecurityUtils.getSubject();
//        String username = "zhangsan";
//        String password = "123456";
//        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//        subject.login(token);
//        System.out.println(securityManager);
//        System.out.println(subject);
//        User user = userService.findOne(11);
//        System.out.println(user);
    }

    @Test
    public void pageInfo() throws Exception {
        UserVO vo = new UserVO();
        vo.setPageNum(2);
        vo.setNumPerPage(2);
        Page<User> userPage = userService.pageInfo(vo);
        CommonUtils.printObj2Json(userPage, logger);
    }

    @Test
    public void findUserByUsername() throws Exception {
        User userByEmail = null;//userService.findUserByUsername("8446666@qq.com11");
        System.out.println(userByEmail);
    }

    @Test
    public void register() throws Exception {
        UserVO vo = new UserVO();
        vo.setPassword("111111");
        vo.setUsername("zhaoliu1");
        userService.register(vo);
    }

    @Test
    public void login() throws Exception {
        UserVO vo = new UserVO();
        vo.setPassword("12345");
        vo.setUsername("wangwu");
        userService.login(vo);
    }
}