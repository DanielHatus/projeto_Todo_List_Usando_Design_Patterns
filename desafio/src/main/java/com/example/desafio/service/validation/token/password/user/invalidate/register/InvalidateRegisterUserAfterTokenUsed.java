package com.example.desafio.service.validation.token.password.user.invalidate.register;

import com.example.desafio.model.register.token.password.user.RegisterPasswordUser;
import com.example.desafio.repository.register.password.user.RegisterUserPasswordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InvalidateRegisterUserAfterTokenUsed {
    private final RegisterUserPasswordRepository repository;

    public InvalidateRegisterUserAfterTokenUsed(RegisterUserPasswordRepository repository) {
        this.repository = repository;
    }

    public void execute(RegisterPasswordUser registerPasswordUser){
        registerPasswordUser.setUsed(true);
        repository.save(registerPasswordUser);
        log.debug("✅ After the token was used, the data True was inserted into the table, and from now on the token used is invalidated.");
    }
}
