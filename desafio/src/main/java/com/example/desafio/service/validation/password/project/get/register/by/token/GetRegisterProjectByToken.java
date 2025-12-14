package com.example.desafio.service.validation.password.project.get.register.by.token;

import com.example.desafio.exceptions.typo.runtime.notfound.NotFoundException;
import com.example.desafio.model.register.password.project.RegisterPasswordProject;
import com.example.desafio.repository.register.password.project.RegisterProjectPasswordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class GetRegisterProjectByToken{

    private final RegisterProjectPasswordRepository repository;

    public GetRegisterProjectByToken(RegisterProjectPasswordRepository repository) {
        this.repository = repository;
    }

    public RegisterPasswordProject getRegisterProjectOrThrow(String token){
        Optional<RegisterPasswordProject> registerPossibleEntity=repository.findByToken(token);
        if (registerPossibleEntity.isPresent()){
            log.debug("✅ A register with token {} was successfully found on the server.",token);
            return registerPossibleEntity.get();
        }
        log.error("❌ No register with token {} was found on the server.",token);
        throw new NotFoundException("No register was found on the server with the token passed in the request.");
    }
}
