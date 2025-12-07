package com.example.desafio.facade.received.password.project.get.token.unique;

import com.example.desafio.repository.register.password.project.RegisterProjectPasswordRepository;
import com.example.desafio.utils.generate.token.password.recovery.GenerateTokenPasswordRecovery;
import org.springframework.stereotype.Component;

@Component
public class GetTokenProjectPasswordUnique{
    private final RegisterProjectPasswordRepository repository;
    private final GenerateTokenPasswordRecovery tokenPasswordRecovery;

    public GetTokenProjectPasswordUnique(
            GenerateTokenPasswordRecovery tokenPasswordRecovery,
            RegisterProjectPasswordRepository repository) {

        this.tokenPasswordRecovery = tokenPasswordRecovery;
        this.repository=repository;
    }

    public String getTokenProjectUnique(){
        String tokenNineElementsGenerated=tokenPasswordRecovery.getNineNumberGenerated();
        while (repository.existsTokenInRegisterProject(tokenNineElementsGenerated)){
            tokenNineElementsGenerated=tokenPasswordRecovery.getNineNumberGenerated();
        }
        return tokenNineElementsGenerated;
    }
}
