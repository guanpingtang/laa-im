package com.dipsy.laa.shiro.config;

import com.dipsy.laa.dao.model.UserInfo;
import com.dipsy.laa.shiro.model.JWTToken;
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
     * 认证,这里我解释一下。正常我们不用jwt token的时候 我们这里只需要数据库查询出用户名密码返回去，shiro框架层会自己做密码匹配，
     * 但是我们用的是jwt token，所以这里我们只需要解析出用户的token是对的，即认为他是一个已经登录成功的用户。实际这里认证已经
     * 成功，但是框架层还不知道登录成功，所以同样需要返回token让他去做匹配
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userToken = (String) token.getCredentials();

        // 解密获得userAccount，用于和数据库进行对比
        String userAccount = JWTUtil.getUserAccount(userToken);

        if (!JWTUtil.verify(userToken, userAccount)) {
            throw new AuthenticationException("无效token");
        }

        UserInfo user = userService.findByUserAccount(userAccount);
        if (user == null) {
            throw new AuthenticationException("用户不存在");
        }

        return new SimpleAuthenticationInfo(userToken, userToken, getName());
    }

}
