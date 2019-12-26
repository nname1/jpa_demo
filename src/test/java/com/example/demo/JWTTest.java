package com.example.demo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Calendar;
import java.util.Date;

public class JWTTest {

    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE,1);
        JwtBuilder builder= Jwts.builder().setId("888")
                .setSubject("小白")
                .setIssuedAt(new Date())//用于设置签发时间
                .setExpiration(c.getTime())
                .signWith(SignatureAlgorithm.HS256,"wangmh")
                .claim("roles","admin")
                .claim("logo","logo.png");//用于设置签名秘钥
        String jwtToken = builder.compact();
        System.out.println(jwtToken);
        //jwtToken="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODgiLCJzdWIiOiLlsI_nmb0iLCJpYXQiOjE1NzcyNTM5NjEsImV4cCI6MTU3NzI1NDU2MX0.xd4Z4OMW3P2a5EKN7E4K-0NYF_56dcu8xd142BwmWiE";
        Claims claims =
                Jwts.parser().setSigningKey("wangmh").parseClaimsJws(jwtToken).getBody();
        System.out.println("id:"+claims.getId());
        System.out.println("subject:"+claims.getSubject());
        System.out.println("IssuedAt:"+claims.getIssuedAt());
        System.out.println("IssuedAt:"+claims.getExpiration());
    }
}
