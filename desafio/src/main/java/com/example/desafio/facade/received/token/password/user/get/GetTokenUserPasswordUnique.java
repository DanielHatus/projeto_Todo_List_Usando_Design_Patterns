package com.example.desafio.facade.received.token.password.user.get;

import com.example.desafio.repository.register.password.user.RegisterUserPasswordRepository;
import com.example.desafio.utils.generate.token.password.recovery.GenerateTokenPasswordRecovery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GetTokenUserPasswordUnique {
    private final RegisterUserPasswordRepository repository;
    private final GenerateTokenPasswordRecovery generateTokenPasswordRecovery;

    public GetTokenUserPasswordUnique(
            RegisterUserPasswordRepository repository,
            GenerateTokenPasswordRecovery generateTokenPasswordRecovery) {
        this.repository = repository;
        this.generateTokenPasswordRecovery=generateTokenPasswordRecovery;
    }

    public String getTokenUnique(){
      String tokenEightNumbersGenerated=generateTokenPasswordRecovery.getNineNumberGenerated();
        while (repository.existsByToken(tokenEightNumbersGenerated)){
         tokenEightNumbersGenerated=generateTokenPasswordRecovery.getNineNumberGenerated();
      }
        log.debug("✅ The unique token for resetting the user's password was successfully created.");
        return tokenEightNumbersGenerated;
    }
}
