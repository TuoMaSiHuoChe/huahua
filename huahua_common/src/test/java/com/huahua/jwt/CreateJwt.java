package com.huahua.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author: admin
 * @Date: 2019/4/23 08:27
 * @Description:
 */
public class CreateJwt {
    public static void main(String[] args) {
        JwtBuilder builder= Jwts.builder().setId("123").setSubject("托马斯小火车").setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256,"huahua");
        String compact = builder.compact();
        System.out.println(compact);
    }
}
