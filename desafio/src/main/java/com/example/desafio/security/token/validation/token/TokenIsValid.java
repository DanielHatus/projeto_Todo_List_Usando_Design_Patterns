package com.example.desafio.security.token.validation.token;

import com.example.desafio.security.token.generate.key.GenerateSecretKeyConverted;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TokenIsValid{
    private final GenerateSecretKeyConverted secretKeyConverted;

    public TokenIsValid(GenerateSecretKeyConverted secretKeyConverted) {
        this.secretKeyConverted = secretKeyConverted;
    }

    public boolean execute(String token){
        try{
            Jwts.parser().setSigningKey(secretKeyConverted.getKeyDecoded()).build()
                    .parseClaimsJws(token);
            log.debug("✅ The token was successfully validated. The user can proceed with the request.");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
