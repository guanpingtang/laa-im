package com.dipsy.laa.common.util;

/**
 * 异常工具类
 *
 * @auther cuiqiongyu
 * @create 2018/5/14 22:29
 */
public class ExceptionUtil {

    public static String getDesc(Throwable throwable) {
        String message = throwable.getMessage();
        if (message != null && message.contains("Exception:")) {
            message = message.substring(message.lastIndexOf("Exception:") + 10);
        }
        return message;
    }

}
