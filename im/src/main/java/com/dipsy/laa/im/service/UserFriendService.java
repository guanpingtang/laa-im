package com.dipsy.laa.im.service;

import com.baomidou.mybatisplus.service.IService;
import com.dipsy.laa.im.model.UserFriend;

public interface UserFriendService extends IService<UserFriend> {

    boolean addFriend(String friendId, String userId);
}
