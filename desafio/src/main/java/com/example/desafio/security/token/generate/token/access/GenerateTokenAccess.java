package com.example.desafio.security.token.generate.token.access;

import com.example.desafio.security.token.generate.key.GenerateSecretKeyConverted;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class GenerateTokenAccess {

    @Value("${jwt.time-expiration.short-expiration}")
    private long shortExpiration;

    private final GenerateSecretKeyConverted secretKeyConverted;

    public GenerateTokenAccess(GenerateSecretKeyConverted secretKeyConverted) {
        this.secretKeyConverted = secretKeyConverted;
    }

    public String generateToken(String username,String email){
        Date now=new Date();
        Date expiryDate=new Date(now.getTime()+shortExpiration);

        return Jwts.builder().subject(username)
                .claim("email",email)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(secretKeyConverted.getKeyDecoded(), SignatureAlgorithm.HS256)
                .compact();
    }
}
