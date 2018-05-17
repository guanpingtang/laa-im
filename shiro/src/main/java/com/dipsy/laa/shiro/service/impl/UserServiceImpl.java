package com.dipsy.laa.shiro.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dipsy.laa.shiro.mapper.UserMapper;
import com.dipsy.laa.shiro.model.UserInfo;
import com.dipsy.laa.shiro.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserInfo> implements UserService {


    /**
     * 根据用户账号查询用户
     *
     * @param userAccount userAccount
     * @return UserInfo
     */
    @Override
    public UserInfo findByUserAccount(String userAccount) {
        Wrapper<UserInfo> wrapper = new EntityWrapper<>();
        wrapper.eq("user_account", userAccount);
        return selectOne(wrapper);
    }
}
