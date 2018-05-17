package com.dipsy.laa.shiro.service;

import com.baomidou.mybatisplus.service.IService;
import com.dipsy.laa.shiro.model.UserInfo;

public interface UserService extends IService<UserInfo> {

    /**
     * 根据用户账号查询用户
     *
     * @param userAccount userAccount
     * @return UserInfo
     */
    UserInfo findByUserAccount(String userAccount);
}
