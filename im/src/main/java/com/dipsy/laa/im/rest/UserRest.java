package com.dipsy.laa.im.rest;

import com.dipsy.laa.im.service.UserFriendService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/friend")
@Api(description = "好友")
public class UserRest {

    @Autowired
    private UserFriendService userFriendService;

    @PostMapping("")
    public boolean addFriend(String friendId) {
        String userId = "1";
        return userFriendService.addFriend(friendId, userId);
    }
}
