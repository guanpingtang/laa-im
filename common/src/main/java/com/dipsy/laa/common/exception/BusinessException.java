package com.dipsy.laa.common.exception;

import com.dipsy.laa.common.constant.IError;

/**
 * 业务异常
 * @author tgp
 */
public class BusinessException extends RuntimeException {

    private String code;
    private String msg;


    public BusinessException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(IError iError) {
        super();
        this.code = iError.getErrorCode();
        this.msg = iError.getErrorMessage();
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
