package com.dipsy.laa.common.web;

import lombok.Data;

/**
 * 通用json响应数据
 * @author tgp
 */
@Data
public class ResponseEntity {

    private boolean isSuccess;
    private Object data;
    private String errorCode;
    private String errorMsg;

    public static ResponseEntity success(Object data) {
        ResponseEntity response = new ResponseEntity();
        response.setSuccess(true);
        response.setData(data);
        return response;
    }

    public static ResponseEntity success() {
        ResponseEntity response = new ResponseEntity();
        response.setSuccess(true);
        return response;
    }

    public static ResponseEntity failure(String errorCode, String errorMsg) {
        ResponseEntity response = new ResponseEntity();
        response.setSuccess(false);
        response.setErrorCode(errorCode);
        response.setErrorMsg(errorMsg);
        return response;
    }
}
