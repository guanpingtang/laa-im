package com.dipsy.laa.im.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dipsy.laa.common.exception.BusinessException;
import com.dipsy.laa.im.mapper.UserFriendMapper;
import com.dipsy.laa.im.model.UserFriend;
import com.dipsy.laa.im.service.UserFriendService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserFriendServiceImpl extends ServiceImpl<UserFriendMapper, UserFriend> implements UserFriendService {

    @Override
    public boolean addFriend(String friendId, String userId) {

        EntityWrapper<UserFriend> entityWrapper = new EntityWrapper<>();
        entityWrapper
            .eq("friend_id", friendId)
            .eq("user_id", userId);
        UserFriend result = selectOne(entityWrapper);
        if (result != null) {
            throw new BusinessException("already exists", "你们已经是朋友关系了");
        }

        UserFriend userFriend = new UserFriend();
        userFriend.setFriendId(friendId);
        userFriend.setUserId(userId);
        userFriend.setCreateAt(new Date());
        userFriend.setUpdateAt(new Date());
        return insert(userFriend);
    }
}
