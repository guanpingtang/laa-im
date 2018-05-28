package com.dipsy.laa.im.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dipsy.laa.common.exception.BusinessException;
import com.dipsy.laa.dao.mapper.UserFriendMapper;
import com.dipsy.laa.dao.model.UserFriend;
import com.dipsy.laa.dao.model.UserInfo;
import com.dipsy.laa.im.service.UserFriendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dipsy.laa.shiro.service.UserService;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
@Slf4j
public class UserFriendServiceImpl extends ServiceImpl<UserFriendMapper, UserFriend> implements UserFriendService {

    @Autowired
    private UserService userService;

    @Override
    public boolean addFriend(String friendId, String userId) {
        // 参数校验
        if (StringUtils.isEmpty(friendId) || StringUtils.isEmpty(userId)) {
            log.debug("用户添加好友失败，userId和friendId都不能为空");
            throw new BusinessException("both the userId and the friendId can't be empty", "userId和friendId都不能为空");
        }
        if (friendId.equals(userId)) {
            log.debug("不能添加自己为好友");
            throw new BusinessException("you can't add yourself as a friend", "不能添加自己为好友");
        }

        EntityWrapper<UserFriend> entityWrapper = friendRelationShipParamBuild(userId, friendId);
        UserFriend result = selectOne(entityWrapper);
        if (null != result) {
            log.debug("你们已经是朋友关系了，userId：{}，friendId：{}", userId, friendId);
            throw new BusinessException("already exists", "你们已经是朋友关系了");
        }

        // 校验用户信息是否存在
        UserInfo friendInfo = userService.findByUserId(friendId);
        UserInfo userInfo = userService.findByUserId(userId);
        if (null == friendInfo || null == userInfo) {
            log.debug("用户添加好友失败，未查询到相关用户或者相关好友信息，{}：{}，{}：{}", userId, JSON.toJSON(userInfo),
                    friendId, JSON.toJSON(friendInfo));
            throw new BusinessException("not querying the relevant user information", "未查询到相关用户信息");
        }

        UserFriend userFriend = new UserFriend();
        userFriend.setFriendId(friendId);
        userFriend.setUserId(userId);
        userFriend.setCreateAt(new Date());
        userFriend.setUpdateAt(new Date());
        return insert(userFriend);
    }

    @Override
    public Set<String> findAllFriendIds(String userId) {
        if (StringUtils.isEmpty(userId)) {
            log.debug("查询用户好友id列表失败！");
            throw new BusinessException("the userId can't be empty", "userId不能为空");
        }

        Set<String> friendIds = new HashSet<>();

        EntityWrapper<UserFriend> entityWrapper = new EntityWrapper<>();
        entityWrapper.and("friend_id", userId).or("user_id", userId);
        List<UserFriend> userFriends = selectList(entityWrapper);
        for (UserFriend userFriend : userFriends) {
            friendIds.add(userFriend.getFriendId());
            friendIds.add(userFriend.getUserId());
        }

        friendIds.remove(userId); // 去除自己
        return friendIds;
    }

    @Override
    public boolean deleteFriend(String userId, String friendId) {
        // 参数校验
        if (StringUtils.isEmpty(friendId) || StringUtils.isEmpty(userId)) {
            log.debug("删除好友失败，userId和friendId都不能为空");
            throw new BusinessException("both the userId and the friendId can't be empty", "userId和friendId都不能为空");
        }

        EntityWrapper<UserFriend> entityWrapper = friendRelationShipParamBuild(userId, friendId);
        return delete(entityWrapper);
    }

    @Override
    public boolean chooseIsFriend(String userId, String friendId) {
        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(friendId)) {
            log.debug("判断是否是好友失败，userId和friendId都不能为空！");
            return false;
        }
        if (userId.equals(friendId)) {
            log.debug("自己跟自己不能说是好友关系");
            return false;
        }

        EntityWrapper<UserFriend> entityWrapper = friendRelationShipParamBuild(userId, friendId);
        UserFriend result = selectOne(entityWrapper);

        return null != result;
    }

    /**
     * 组装userId和friendId的查询参数
     * @param userId
     * @param friendId
     * @return
     */
    private EntityWrapper<UserFriend> friendRelationShipParamBuild(String userId, String friendId) {
        List<String> receiverIds = new ArrayList<>();
        receiverIds.add(userId);
        receiverIds.add(friendId);
        EntityWrapper<UserFriend> entityWrapper = new EntityWrapper<>();
        entityWrapper.in("friend_id", receiverIds).in("user_id", receiverIds);
        return entityWrapper;
    }

}
