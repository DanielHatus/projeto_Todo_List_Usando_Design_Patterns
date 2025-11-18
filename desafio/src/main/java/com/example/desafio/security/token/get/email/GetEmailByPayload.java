package com.example.desafio.security.token.get.email;

import com.example.desafio.security.token.generate.key.GenerateSecretKeyConverted;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class GetEmailByPayload{

    private final GenerateSecretKeyConverted secretKeyConverted;

    public GetEmailByPayload(GenerateSecretKeyConverted secretKeyConverted) {
        this.secretKeyConverted = secretKeyConverted;
    }

    public String execute(String token){
        return Jwts.parser().setSigningKey(secretKeyConverted.getKeyDecoded())
                .build()
                .parseClaimsJws(token)
                .getPayload()
                .get("email",String.class);
    }
}
