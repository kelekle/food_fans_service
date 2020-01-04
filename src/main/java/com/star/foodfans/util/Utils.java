package com.star.foodfans.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Utils {

    public static final String resource_prefix = "http://192.168.0.109:8085/";

    // token秘钥  太短会报错
    public static final String SECRET = "qwerasdfdxzvdfajjlkjeiojznvxndjkfaowijjjdddlll";
    //6 hours
    public static final long TTL_MILLIS = 6 * 3600 * 1000;

    /**
     * 生成Jwt的方法
     *
     * @param id 用户ID
     * @return Token String 凭证
     */
    public static String createJWT(Integer id) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        long expMillis = nowMillis + TTL_MILLIS;
        Date exp = new Date(expMillis);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = JWT.create()
                .withHeader(map)
                .withClaim("uid", id)
                .withExpiresAt(exp)
                .withNotBefore(now)
                .withIssuedAt(now)
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }

    /**
     * 生成6位随机验证码
     * @return code
     */
    public static String generateEmailCode(){
        String str = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder code = new StringBuilder();
        for(int i = 0;i < 6;i++){
            int index = (int) (Math.random() * str.length());
            code.append(str.charAt(index));
        }
        return code.toString();
    }


}
