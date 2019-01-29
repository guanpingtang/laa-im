package com.dipsy.laa.shiro.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dipsy.laa.common.Annotation.Rule;
import com.dipsy.laa.dao.mapper.UserMapper;
import com.dipsy.laa.dao.model.UserInfo;
import com.dipsy.laa.shiro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Rule
public class UserServiceImpl extends ServiceImpl<UserMapper, UserInfo> implements UserService {

    @Override
    public UserInfo findByUserAccount(String userAccount) {
        if (StringUtils.isEmpty(userAccount)) {
            log.debug("查询用户信息失败，userAccont不能为空！");
            return null;
        }

        Wrapper<UserInfo> wrapper = new EntityWrapper<>();
        wrapper.eq("user_account", userAccount);
        return selectOne(wrapper);
    }

    @Override
    public UserInfo findByUserId(String userId) {
        if (StringUtils.isEmpty(userId)) {
            log.debug("查询用户信息失败，userId不能为空！");
            return null;
        }

        return selectById(userId);
    }

}
