package com.xiaobai.websocket.util;

import com.xiaobai.websocket.sdk.exception.BusinessRuntimeException;
import com.xiaobai.websocket.user.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    public static final String secret = "abcdefghijklmnopqrstuvwxyz";

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    public static String createToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims)
        //设置ID，据说能防止重放攻击
                .setId(String.valueOf(System.currentTimeMillis()))
                //设置主题
                .setSubject("eugeneMa")
                //设置签发时间
                .setIssuedAt(new Date())
                //设置过期时间
                .setExpiration(new Date(System.currentTimeMillis() + 7200000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }


    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    public static Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    /**
     * 根据身份信息获取键值
     *
     * @param claims 身份信息
     * @param key    键
     * @return 值
     */
    public static String getValue(Claims claims, String key) {
        if(claims == null){
            return "";
        }
        Object ob = claims.get(key);
        return StringUtils.isEmpty(ob) ? "" : ob.toString();
    }

    public static User checkToken(String token) {
        Claims claims;
        try {
            claims = JwtUtils.parseToken(token);
        } catch (Exception e) {
            throw new BusinessRuntimeException("-1", "token 校验失败");
        }
        if (claims == null) {
            throw new BusinessRuntimeException("-1", "token 已失效");
        }
        User user = new User();
        user.setUserName(JwtUtils.getValue(claims, "userName"));
        user.setId(Long.valueOf(JwtUtils.getValue(claims, "id")));
        return user;

    }

    public static String getToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userName", user.getUserName());
        claims.put("id", user.getId());
        return JwtUtils.createToken(claims);
    }
}
