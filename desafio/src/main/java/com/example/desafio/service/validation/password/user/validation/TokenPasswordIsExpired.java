package com.example.desafio.service.validation.password.user.validation;

import com.example.desafio.exceptions.typo.runtime.badrequest.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class TokenPasswordIsExpired{
    public void ifExpiredReturnThrow(LocalDateTime dateTimeRegister){
        if (LocalDateTime.now().isAfter(dateTimeRegister)){
            log.error("❌ The token has expired. Therefore, it is not possible to use this token again today.");
            throw new BadRequestException("The token has expired because its usage time limit of 5 minutes has passed. " +
                    "Please generate a new token and try again.");
        }
    }
}
