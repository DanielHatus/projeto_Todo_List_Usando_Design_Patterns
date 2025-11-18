package com.example.desafio.service.authentication.login.token;

import com.example.desafio.dto.response.authentication.ResponseDtoTokens;
import com.example.desafio.security.token.generate.token.access.GenerateTokenAccess;
import com.example.desafio.security.token.generate.token.refresh.GenerateTokenRefresh;
import com.example.desafio.service.authentication.implementations.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GenerateTokensLoginService {
    private final GenerateTokenAccess tokenAccess;
    private final GenerateTokenRefresh tokenRefresh;

    public GenerateTokensLoginService(GenerateTokenAccess tokenAccess, GenerateTokenRefresh tokenRefresh) {
        this.tokenAccess = tokenAccess;
        this.tokenRefresh = tokenRefresh;
    }

    public ResponseDtoTokens generateTokens(CustomUserDetails userDetails){

        log.debug("✅ Starting token generation after the user has been authenticated.");
        return new ResponseDtoTokens(
         tokenAccess.generateToken(userDetails.getUsername(), userDetails.getEmail()),
         tokenRefresh.generateToken(userDetails.getUsername(),userDetails.getEmail())
        );
    }
}
