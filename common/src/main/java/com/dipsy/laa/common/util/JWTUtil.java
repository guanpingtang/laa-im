package com.dipsy.laa.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;

public class JWTUtil {

    private static final String SECRET = "we are team";

    // 过期时间5分钟
    private static final long EXPIRE_TIME = 5 * 60 * 1000;

    /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @param userAccount 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String userAccount) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                .withClaim("userAccount", userAccount)
                .build();
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户账号
     */
    public static String getUserAccount(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userAccount").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户账号
     */
    public static String getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userId").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成token,5min后过期
     *
     * @param userAccount 用户账号
     * @param userId 用户id
     * @return 加密的token
     */
    public static String sign(String userAccount, String userId) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            // 附带userAccount信息
            return JWT.create()
                .withClaim("userAccount", userAccount)
                .withClaim("userId", userId)
                .withExpiresAt(date)
                .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
}