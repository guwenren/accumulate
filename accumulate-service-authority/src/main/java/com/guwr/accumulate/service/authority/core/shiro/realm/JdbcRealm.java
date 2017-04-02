package com.guwr.accumulate.service.authority.core.shiro.realm;

import com.guwr.accumulate.facade.authority.entity.User;
import com.guwr.accumulate.service.authority.core.service.IPermissionService;
import com.guwr.accumulate.service.authority.core.service.IRoleService;
import com.guwr.accumulate.service.authority.core.service.IUserService;
import com.guwr.accumulate.service.authority.core.shiro.token.TokenManager;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.authority.core.JdbcRealm
 * Date 2016/9/2
 * Time 21:49
 */
public class JdbcRealm extends AuthorizingRealm {
    private static Logger logger = LoggerFactory.getLogger(JdbcRealm.class);
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IPermissionService permissionService;

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("授权 doGetAuthorizationInfo");
        User user = TokenManager.getToken();
        Integer uid = user.getId();
        Set<String> roles = roleService.findRoleByUId(uid);
        Set<String> permissions = permissionService.findPermissionByUid(uid);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roles);
        info.setStringPermissions(permissions);
        return info;
    }

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("认证.doGetAuthenticationInfo");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        char[] password = token.getPassword();
        User user = userService.findUserByUsername(username);

        String s = String.valueOf(password);
        //加密后的密码
        String password1 = user.getPassword();
        String password2 = new Md5Hash(s, user.getSalt() + user.getUsername()).toHex();
        if (!password1.equals(password2)) {
            throw new IncorrectCredentialsException("帐号或密码错误");
        }
//        DisabledAccountException（禁用的帐号）
//        LockedAccountException（锁定的帐号）
//        UnknownAccountException（错误的帐号）
//        ExcessiveAttemptsException（登录失败次数过多）
//        IncorrectCredentialsException （错误的凭证）
//        ExpiredCredentialsException（过期的凭证）
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
    }


}
