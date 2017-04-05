package com.guwr.accumulate.web.realm;

import com.guwr.accumulate.facade.authority.entity.User;
import com.guwr.accumulate.facade.authority.facade.IPermissionFacade;
import com.guwr.accumulate.facade.authority.facade.IRoleFacade;
import com.guwr.accumulate.facade.authority.facade.IUserFacade;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by guwr
 * Project_name accumulate
 * Path com.guwr.accumulate.web.realm.JdbcRealm
 * Date 2017/4/5
 * Time 14:03
 * Description
 */
public class JdbcRealm extends AuthorizingRealm {

    private static Logger logger = LoggerFactory.getLogger(JdbcRealm.class);
    @Autowired
    private IUserFacade userFacade;
    @Autowired
    private IRoleFacade roleFacade;
    @Autowired
    private IPermissionFacade permissionFacade;


    private Integer uid;

    public static void main(String[] args) {
        String hashAlgorithmName = "MD5";
        Object credentials = "admin";
        Object salt = ByteSource.Util.bytes("admin");
        int hashIterations = 2;
        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        System.out.println(result);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("认证");
        Object primaryPrincipal = principalCollection.getPrimaryPrincipal();
        Set<String> roles = new HashSet<>();
        Set<String> stringPermissions = new HashSet<>();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        info.setRoles(roles);
        info.setStringPermissions(stringPermissions);

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("授权");

        String username = (String) authenticationToken.getPrincipal();

        logger.info("username = {}", username);

        User user = userFacade.findUserByUsername(username);

        uid = user.getId();
        String salt = user.getSalt();
        logger.info("user = {}", user);
        Object principal = username;
        Object hashedCredentials = user.getPassword();
        ByteSource credentialsSalt = ByteSource.Util.bytes(salt);
        String realmName = getName();

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, hashedCredentials, credentialsSalt, realmName);

        return info;
    }
}
