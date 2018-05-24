package com.dipsy.laa.im.service;

import com.baomidou.mybatisplus.service.IService;
import com.dipsy.laa.dao.model.UserFriend;
import java.util.Set;

public interface UserFriendService extends IService<UserFriend> {

    /**
     * 添加好友（双向关系）
     * @param friendId friendId
     * @param userId userId
     * @return 是否添加成功
     */
    boolean addFriend(String friendId, String userId);

    /**
     * 查询用户好友id列表
     * @param userId 当前用户id
     * @return 是否添加成功
     */
    Set<String> findAllFriendIds(String userId);

    /**
     * 删除好友关系（双向关系，一方删除，另一方也没有了）
     * @param userId userId
     * @param friendId friendId
     * @return 是否删除成功
     */
    boolean deleteFriend(String userId, String friendId);

    /**
     * 判断两个用户之间是否是好友关系
     * @param userId userId
     * @param friendId friendId
     * @return 是否是好友关系
     */
    boolean chooseIsFriend(String userId, String friendId);

}
