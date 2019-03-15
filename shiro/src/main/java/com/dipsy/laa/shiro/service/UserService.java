package com.dipsy.laa.shiro.service;

import com.baomidou.mybatisplus.service.IService;
import com.dipsy.laa.shiro.model.UserInfo;

public interface UserService extends IService<UserInfo> {

    /**
     * 根据用户账号查询用户信息
     * @param userAccount userAccount
     * @return UserInfo
     */
    UserInfo findByUserAccount(String userAccount);

    /**
     * 根据userId查询用户信息
     * @param userId userId
     * @return UserInfo
     */
    UserInfo findByUserId(String userId);

}
