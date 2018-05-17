package com.dipsy.laa.shiro.config;

import com.dipsy.laa.shiro.model.JWTToken;
import com.dipsy.laa.shiro.model.UserInfo;
import com.dipsy.laa.shiro.service.UserService;
import com.dipsy.laa.shiro.util.JWTUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 权限判断  只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //暂时没有权限
        return null;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userToken = (String) token.getCredentials();

        // 解密获得userAccount，用于和数据库进行对比
        String userAccount = JWTUtil.getUserAccount(userToken);
        if (userAccount == null) {
            throw new AuthenticationException("token invalid");
        }

        UserInfo user = userService.findByUserAccount(userAccount);
        if (user == null) {
            throw new UnknownAccountException();
        }

        if (!JWTUtil.verify(userToken, userAccount, user.getPassword())) {
            throw new UnknownAccountException();
        }

        return new SimpleAuthenticationInfo(userToken, userToken, "my_realm");
    }
}
