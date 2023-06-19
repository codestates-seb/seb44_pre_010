package com.rainbow.sof.user.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.*;

@Component
public class JwtTokenizer {
    @Getter
    @Value("thisIsTestScretKeythisIsTestScretKeythisIsTestScretKeythisIsTestScretKey")
    private String secretKeySting;

    @Getter
    @Value("60")
    private int accessTokenExpirationMinutes;
    @Getter
    @Value("300")
    private int refreshTokenExpirationMinutes;

    //시크릿키로 이용할 무작위 문자열을 바이트배열로 변환
    public String secretKeyEncodeBase64(String secretKeySting){
        return Encoders.BASE64.encode(secretKeySting.getBytes(StandardCharsets.UTF_8));
    }

    //엑세스 토큰(인증을 위한 claims 추가)
    public String generateAccessToken(Map<String, Object>claims,
                                      String subject,
                                      Date expiration,
                                      String base64EncodedSecretKeyString){
        Key key = getKeyBase64DecodedKey(base64EncodedSecretKeyString);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setExpiration(expiration)
                .setIssuedAt(Calendar.getInstance().getTime())
                .signWith(key)
                .compact();

    }
    //리프레시 토큰
    public String generateRefreshToken(String subject,
                                       Date expiration,
                                       String base64EncodedSecretKeyString){
        Key key = getKeyBase64DecodedKey(base64EncodedSecretKeyString);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(Calendar.getInstance().getTime())
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }


    //claims 획득(인증 겸용 메서드)
    public Jws<Claims> getClaims(String jws, String base64EncodedSecretKeyString){
        Key key = getKeyBase64DecodedKey(base64EncodedSecretKeyString);

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jws);

    }

    //가져온 키를 다시 복호화
    private Key getKeyBase64DecodedKey(String base64EncodedSecretKeyString) {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(base64EncodedSecretKeyString));
    }

    //토큰의 만료시간설정
    private Date getTokenExpiration(int ExpirationMinutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE,ExpirationMinutes);

        return calendar.getTime();
    }


}
