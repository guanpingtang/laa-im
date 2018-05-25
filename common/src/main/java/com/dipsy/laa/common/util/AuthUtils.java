package com.dipsy.laa.common.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * 获取当前登录用户,在controller中使用
 */
public class AuthUtils {

    /**
     * 获取当前登录用户
     * @return userId
     */
    public static String getCurrUserId() {
        Subject subject = SecurityUtils.getSubject();
        String token = (String) subject.getPrincipal();
        return JWTUtil.getUserId(token);
    }
}
