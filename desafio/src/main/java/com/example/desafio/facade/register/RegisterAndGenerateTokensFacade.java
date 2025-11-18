package com.example.desafio.facade.register;

import com.example.desafio.dto.request.authentication.UserRegisterDto;
import com.example.desafio.dto.response.authentication.ResponseDtoTokens;
import com.example.desafio.model.user.User;
import com.example.desafio.service.authentication.register.token.GenerateTokensRegisterService;
import com.example.desafio.service.authentication.register.user.UserRegisterService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
public class RegisterAndGenerateTokensFacade {
    private final UserRegisterService registerService;
    private final GenerateTokensRegisterService tokensRegisterService;

    public RegisterAndGenerateTokensFacade(UserRegisterService registerService, GenerateTokensRegisterService tokensRegisterService) {
        this.registerService = registerService;
        this.tokensRegisterService = tokensRegisterService;
    }

    @Transactional
    public ResponseDtoTokens execute(UserRegisterDto userRegisterDto){
        User entity=registerService.registerUser(userRegisterDto);
        ResponseDtoTokens tokens=tokensRegisterService.generateTokens(entity);
        log.debug("✅ User {} successfully registered and token successfully generated.",entity.getUsername());
        return tokens;
    }
}
