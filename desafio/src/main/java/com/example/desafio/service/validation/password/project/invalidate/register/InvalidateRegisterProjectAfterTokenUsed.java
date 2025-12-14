package com.example.desafio.service.validation.password.project.invalidate.register;

import com.example.desafio.model.register.password.project.RegisterPasswordProject;
import com.example.desafio.repository.register.password.project.RegisterProjectPasswordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InvalidateRegisterProjectAfterTokenUsed{
    private final RegisterProjectPasswordRepository repository;

    public InvalidateRegisterProjectAfterTokenUsed(RegisterProjectPasswordRepository repository) {
        this.repository = repository;
    }

    public void execute(RegisterPasswordProject registerPasswordProject){
        registerPasswordProject.setUsed(true);
        repository.save(registerPasswordProject);
        log.debug("✅ After the token was used, the data True was inserted into the table, and from now on the token used is invalidated.");
    }
}
