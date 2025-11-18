package com.example.desafio.security.token.generate.key;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;

@Component

public class GenerateSecretKeyConverted {
    @Value("${jwt.secret-key.key-base64}")
    private String keyBase64;

    public Key getKeyDecoded(){
        byte[] bytesKey= Base64.getDecoder().decode(keyBase64);
        return Keys.hmacShaKeyFor(bytesKey);
    }
}
