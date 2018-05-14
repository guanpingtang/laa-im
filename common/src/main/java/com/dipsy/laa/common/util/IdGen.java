package com.dipsy.laa.common.util;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * 封装各种生成唯一性ID算法的工具类.
 *
 * @auther cuiqiongyu
 * @create 2018/5/14 22:33
 */
public class IdGen {

    private static SecureRandom random = new SecureRandom();

    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 使用SecureRandom随机生成Long.
     */
    public static long randomLong() {
        return Math.abs(random.nextLong());
    }

    /**
     * 使用SecureRandom随机生成Integer.
     */
    public static int randomInt() {
        return Math.abs(random.nextInt());
    }

}
