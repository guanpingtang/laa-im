package com.dipsy.laa.shiro.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.dipsy.laa.shiro.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户mapper
 */
@Mapper
public interface UserMapper extends BaseMapper<UserInfo> {
}
