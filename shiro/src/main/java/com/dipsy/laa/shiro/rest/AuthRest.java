package com.dipsy.laa.shiro.rest;

import com.dipsy.laa.common.web.ResponseEntity;
import com.dipsy.laa.shiro.model.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authweq")
@Api(description = "登录")
public class AuthRest {

    @GetMapping("/hash")
    @ApiOperation(value = "密码加密example")
    public ResponseEntity hash(String userAccount, String password) {
        String hashPassword = new SimpleHash("MD5" , password, ByteSource.Util.bytes(userAccount),
            2).toHex();
        return ResponseEntity.success(hashPassword);
    }

    @GetMapping("/login")
    @ApiOperation(value = "登录")
    public ResponseEntity auth(String userAccount, String password) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1);
       return ResponseEntity.success(userInfo);
    }
}
