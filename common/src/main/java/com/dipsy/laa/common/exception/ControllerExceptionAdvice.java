package com.dipsy.laa.common.exception;

import com.dipsy.laa.common.web.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常捕获类
 * @author tgp
 */
@RestControllerAdvice
public class ControllerExceptionAdvice {

    /**
     * 业务异常
     * @param request request
     * @param e e
     * @return  ResponseEntity
     */
    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity handleBusinessException(HttpServletRequest request, BusinessException e) {
        return ResponseEntity.failure(e.getCode(), e.getMsg());
    }

//    /**
//     * 当异常无法匹配时，将会在这里处理.
//     */
//    @ResponseBody
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//    public ResponseEntity handleException(HttpServletRequest request, BusinessException e) {
//        return ResponseEntity.failure("500","内部异常");
//    }

}
