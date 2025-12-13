package com.example.desafio.facade.register.user;

import com.example.desafio.dto.request.authentication.UserRegisterDto;
import com.example.desafio.dto.response.authentication.ResponseDtoTokens;
import com.example.desafio.model.user.User;
import com.example.desafio.service.authentication.register.token.GenerateTokensRegisterService;
import com.example.desafio.service.authentication.register.user.UserRegisterService;
import com.example.desafio.service.authentication.register.validation.EmailAndUsernameIsUnique;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
public class RegisterAndGenerateTokensFacade {
    private final UserRegisterService registerService;
    private final GenerateTokensRegisterService tokensRegisterService;
    private final EmailAndUsernameIsUnique emailAndUsernameIsUnique;

    public RegisterAndGenerateTokensFacade(
        UserRegisterService registerService,
        GenerateTokensRegisterService tokensRegisterService,
        EmailAndUsernameIsUnique emailAndUsernameIsUnique) {

        this.registerService = registerService;
        this.tokensRegisterService = tokensRegisterService;
        this.emailAndUsernameIsUnique = emailAndUsernameIsUnique;
    }

    @Transactional
    public ResponseDtoTokens execute(UserRegisterDto userRegisterDto){
        emailAndUsernameIsUnique.validationIfEmailAndUsernameIsUnique(userRegisterDto.getEmail(), userRegisterDto.getUsername());
        User entity=registerService.registerUser(userRegisterDto);
        ResponseDtoTokens tokens=tokensRegisterService.generateTokens(entity);
        log.debug("✅ User {} successfully registered and token successfully generated.",entity.getUsername());
        return tokens;
    }
}
