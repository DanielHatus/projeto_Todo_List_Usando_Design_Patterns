package com.example.desafio.utils.validation.token.from.reset.password.is.used;

import com.example.desafio.exceptions.typo.runtime.badrequest.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TokenPasswordIsUsed{

    public void validationIfTokenIsUsedAndThrowIfUsed(boolean tokenIsUsed){
        if (tokenIsUsed){
            log.error("The token passed in the request has already been used.");
            throw new BadRequestException("The token passed in the request has already been used. Please generate a new token and try again.");
        }
    }
}
