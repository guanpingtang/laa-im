package com.dipsy.laa.shiro.rest;

import com.dipsy.laa.common.exception.BusinessException;
import com.dipsy.laa.common.util.AuthUtils;
import com.dipsy.laa.common.web.ResponseEntity;
import com.dipsy.laa.dao.model.UserInfo;
import com.dipsy.laa.shiro.constant.ShiroError;
import com.dipsy.laa.shiro.service.UserService;
import com.dipsy.laa.common.util.JWTUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Api(description = "登录")
public class AuthRest {

    @Autowired
    private UserService userService;

    @GetMapping("/hash")
    @ApiOperation(value = "密码加密example")
    public ResponseEntity hash(
        @RequestParam("password") String password) {
        String userId = AuthUtils.getCurrUserId();
        System.out.println(userId);
        String hashPassword = new SimpleHash("MD5", password, ByteSource.Util.bytes(password),
            2).toHex();
        return ResponseEntity.success(hashPassword);
    }

    @GetMapping("/login")
    @ApiOperation(value = "登录")
    public ResponseEntity auth(
        @RequestParam("userAccount") String userAccount,
        @RequestParam("password") String password) {

        UserInfo user = userService.findByUserAccount(userAccount);
        if (user == null) {
            throw new BusinessException(ShiroError.USERNAME_OR_PASSWORD_ERROR);
        }
        String pwd = new SimpleHash("MD5", password, ByteSource.Util.bytes(password), 2)
            .toHex();
        if (!pwd.equals(user.getPassword())) {
            throw new BusinessException(ShiroError.USERNAME_OR_PASSWORD_ERROR);
        }
        return ResponseEntity.success(JWTUtil.sign(userAccount, user.getUserId()));
    }

}
