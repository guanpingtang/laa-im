package com.dipsy.laa.common.constant;

/**
 * 公有模块错误枚举
 * @author tgp
 */
public enum ErrorEnum implements IError {

    PERMISSION_LIMITED("permission_limited","访问受限");

    private String errorCode;
    private String errorMessage;



    ErrorEnum(String errorCode, String errorMessage) {
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