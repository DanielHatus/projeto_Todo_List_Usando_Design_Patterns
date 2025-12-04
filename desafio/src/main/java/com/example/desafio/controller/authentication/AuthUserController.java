package com.example.desafio.controller.authentication;

import com.example.desafio.docs.authetication.AuthUserDoc;
import com.example.desafio.dto.request.authentication.UserLoginDto;
import com.example.desafio.dto.request.authentication.UserRegisterDto;
import com.example.desafio.dto.response.authentication.ResponseDtoTokens;
import com.example.desafio.facade.login.user.LoginAndGenerateTokensFacade;
import com.example.desafio.facade.register.user.RegisterAndGenerateTokensFacade;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class AuthUserController implements AuthUserDoc {
    private final RegisterAndGenerateTokensFacade registerAndGenerateTokensFacade;
    private final LoginAndGenerateTokensFacade loginAndGenerateTokensFacade;

    public AuthUserController(RegisterAndGenerateTokensFacade registerAndGenerateTokensFacade, LoginAndGenerateTokensFacade loginAndGenerateTokensFacade) {
        this.registerAndGenerateTokensFacade = registerAndGenerateTokensFacade;
        this.loginAndGenerateTokensFacade = loginAndGenerateTokensFacade;
    }

    @Override
    @PostMapping(value = "/register",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDtoTokens> registerAndGenerateTokens(@RequestBody @Valid UserRegisterDto userRegisterDto) {
        return ResponseEntity.ok(registerAndGenerateTokensFacade.execute(userRegisterDto));
    }

    @Override
    @PostMapping(value = "/login",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDtoTokens> loginUserAndGenerateTokens(@RequestBody @Valid UserLoginDto userLoginDto) {
        return ResponseEntity.ok(loginAndGenerateTokensFacade.execute(userLoginDto));
    }
}
