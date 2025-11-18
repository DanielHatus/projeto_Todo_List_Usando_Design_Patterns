package com.example.desafio.service.validation.password.validation;

import com.example.desafio.exceptions.typo.runtime.badrequest.BadRequestException;
import com.example.desafio.service.validation.password.utils.get.GetRegisterByToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TokenPasswordIsUsed {
private final GetRegisterByToken registerByToken;

    public TokenPasswordIsUsed(GetRegisterByToken registerByToken) {
        this.registerByToken = registerByToken;
    }

    public void ifUsedReturnThrow(boolean tokenIsUsed,String token){
        if (tokenIsUsed){
        log.error("The token {} passed in the request has already been used.",token);
        throw new BadRequestException("The token passed in the request has already been used. Please generate a new token and try again.");
        }
    }


}
