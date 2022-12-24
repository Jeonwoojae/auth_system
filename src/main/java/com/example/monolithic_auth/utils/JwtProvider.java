package com.example.monolithic_auth.utils;

import com.example.monolithic_auth.domain.Users;
import io.jsonwebtoken.*;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;



@Service
@Slf4j
public class JwtProvider{
//    @Value("${jwt.secret}")
    private String SECRET_KEY = "jefnewjfnewfehfvahwefjwfjewkfabfjkfefwefwfafefawfeffdewfewfeffwf";
//    @Value("${jwt.expired-time")
    private long EXP = 634000;

//  보통 subject는 유저의 아이디 사용하여 토큰을 만든다.
    public String createToken(Users user){
        SignatureAlgorithm signatureAlgorithm =SignatureAlgorithm.HS256;

//        string 형태 키를 바이트로 변경
        byte[] secretKeyBytes = Decoders.BASE64.decode(SECRET_KEY);
        Key signingKey = Keys.hmacShaKeyFor(secretKeyBytes);

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("woojae")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXP))
                .claim("role",user.getRole().getKey())
                .signWith(signingKey, signatureAlgorithm)
                .compact();
    }

//    토큰 정보 가져오기
    public String getSubject(String token){
       try {
           Jws<Claims> claims = Jwts.parserBuilder()
                   .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                   .build()
                   .parseClaimsJws(token);
           Claims body = claims.getBody();

           System.out.println(body);
//        토큰의 많은 정보들 중 사용할 서브젝트만
           return body.get("role",String.class);
           
//         유효성 검사
       } catch (SignatureException e){
           log.error("JWT의 시그니쳐가 잘못됨", e);
       } catch (MalformedJwtException e){
           log.error("jwt token이 잘못됨", e);
       } catch (ExpiredJwtException e){
           log.error("토큰이 만료됨", e);
       } catch (UnsupportedJwtException e){
           log.error("지원하지 않는 토큰", e);
       } catch (IllegalArgumentException e){
           log.error("데이터가 비었음", e);
       }
        return null;
    }
}
