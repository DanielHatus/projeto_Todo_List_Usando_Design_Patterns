package com.example.desafio.facade.login.user;

import com.example.desafio.dto.request.authentication.UserLoginDto;
import com.example.desafio.dto.response.authentication.ResponseDtoTokens;
import com.example.desafio.service.authentication.implementations.CustomUserDetails;
import com.example.desafio.service.authentication.login.token.GenerateTokensLoginService;
import com.example.desafio.service.authentication.login.user.LoginUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class LoginAndGenerateTokensFacade {
    private final LoginUserService loginUserService;
    private final GenerateTokensLoginService generateTokensLoginService;

    public LoginAndGenerateTokensFacade(
            LoginUserService loginUserService,
            GenerateTokensLoginService generateTokensLoginService) {

        this.loginUserService = loginUserService;
        this.generateTokensLoginService = generateTokensLoginService;
    }

    @Transactional(readOnly = true)
    public ResponseDtoTokens execute(UserLoginDto userLoginDto){

       CustomUserDetails userDetails=loginUserService.loginUser(userLoginDto);

       ResponseDtoTokens tokens=generateTokensLoginService.generateTokens(userDetails);
       log.debug("✅ tokens generated successfully");

       return tokens;
    }
}
