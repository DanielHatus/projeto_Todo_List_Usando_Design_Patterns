package com.example.desafio.service.validation.password.invalidate.register;

import com.example.desafio.model.register.password.RegisterPassword;
import com.example.desafio.repository.register.password.RegisterPasswordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InvalidateRegisterAfterUseToken {
    private final RegisterPasswordRepository repository;

    public InvalidateRegisterAfterUseToken(RegisterPasswordRepository repository) {
        this.repository = repository;
    }

    public void execute(RegisterPassword registerPassword){
        registerPassword.setUsed(true);
        repository.save(registerPassword);
        log.info("✅ After the token was used, the data True was inserted into the table, and from now on the token used is invalidated.");
    }
}
