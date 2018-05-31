package com.dipsy.laa.im.service.impl;

import com.dipsy.laa.im.service.UserOnlineService;

import java.nio.channels.Channel;
import java.util.Map;

public class UserOnlineServiceImpl implements UserOnlineService {

    private static Map<String, Channel> map;

    @Override
    public boolean addUserOnline() {
        return false;
    }
}
