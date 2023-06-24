package com.rainbow.sof.domain.user.auth.jwt;

import com.rainbow.sof.domain.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
public class DelegateTokenService {
    private final JwtTokenizer tokenizer;

    public DelegateTokenService(JwtTokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public String delegateAccessToken(User user){
        Map<String,Object> claims = new HashMap<>();
        claims.put("email",user.getEmail());

        Date expiration = tokenizer.getTokenExpiration(tokenizer.getAccessTokenExpirationMinutes());
        String base64EncodedSecretKeyString = tokenizer.secretKeyEncodeBase64(tokenizer.getSecretKeySting());
        String subject=user.getEmail();

        return tokenizer.generateAccessToken(claims,subject,expiration,base64EncodedSecretKeyString);

    }

    public String delegateRefreshToken(User user){

        Date expiration = tokenizer.getTokenExpiration(tokenizer.getAccessTokenExpirationMinutes());
        String base64EncodedSecretKeyString = tokenizer.secretKeyEncodeBase64(tokenizer.getSecretKeySting());
        String subject=user.getEmail();

        return tokenizer.generateRefreshToken(subject,expiration,base64EncodedSecretKeyString);

    }
}
