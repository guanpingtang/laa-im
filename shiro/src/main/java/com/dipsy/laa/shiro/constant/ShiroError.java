package com.dipsy.laa.shiro.constant;

import com.dipsy.laa.common.constant.IError;

public enum ShiroError implements IError {

    USERNAME_OR_PASSWORD_ERROR("username_or_password_error", "用户名或密码错误"),
    USER_NOT_EXIST("user_not_exist","用户不存在"),
    TOKEN_INCALID("token_invalid", "无效token"),
    NOT_LOGIN("not_login", "未登录");

    private String errorCode;
    private String errorMessage;

    ShiroError(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
