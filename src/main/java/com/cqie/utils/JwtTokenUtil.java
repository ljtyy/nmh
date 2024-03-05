package com.cqie.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@Component
public class JwtTokenUtil {

    // 创建一个秘钥
    public static final String TOKEN_KEY = "javasdfasdfasdfxixiahahahehe";

    // 设置token的有效期  15min
    public final static long KEEP_TIME = 1000 * 60 * 60 * 2;

    /**
     * 生成token
     *
     * @param accountName 用户名
     * @param userId
     * @return token
     */
    public static String buildJwt(String accountName, String userId) {
        Date date = new Date(System.currentTimeMillis() + KEEP_TIME);
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_KEY);
        // 设置头部信息
        Map header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        return JWT.create()
                .withHeader(header)
                .withClaim("loginName", accountName)
                .withClaim("userId", userId)
                .withExpiresAt(date)
                .sign(algorithm);
    }


    /**
     * 校验token是否正确
     *
     * @param token
     * @return
     */
    public static boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_KEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }


    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("loginName").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获取登陆用户ID
     *
     * @param token
     * @return
     */
    public static String getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userId").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
}
