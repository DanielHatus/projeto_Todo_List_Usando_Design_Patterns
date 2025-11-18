package com.example.desafio.service.authentication.register.token;

import com.example.desafio.dto.response.authentication.ResponseDtoTokens;
import com.example.desafio.model.user.User;
import com.example.desafio.security.token.generate.token.access.GenerateTokenAccess;
import com.example.desafio.security.token.generate.token.refresh.GenerateTokenRefresh;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GenerateTokensRegisterService {
    private final GenerateTokenAccess tokenAccess;
    private final GenerateTokenRefresh tokenRefresh;

    public GenerateTokensRegisterService(GenerateTokenAccess tokenAccess, GenerateTokenRefresh tokenRefresh) {
        this.tokenAccess = tokenAccess;
        this.tokenRefresh = tokenRefresh;
    }

    public ResponseDtoTokens generateTokens(User entity){
        log.debug("✅ Starting token generation after the user has been saved in database.");
        return new ResponseDtoTokens(
                tokenAccess.generateToken(entity.getUsername(), entity.getEmail()),
                tokenRefresh.generateToken(entity.getUsername(),entity.getEmail())
        );
    }
}
