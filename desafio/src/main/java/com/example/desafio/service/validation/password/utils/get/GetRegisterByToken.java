package com.example.desafio.service.validation.password.utils.get;

import com.example.desafio.exceptions.typo.runtime.notfound.NotFoundException;
import com.example.desafio.model.register.password.RegisterPassword;
import com.example.desafio.repository.register.password.RegisterPasswordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class GetRegisterByToken{
    private final RegisterPasswordRepository repository;

    public GetRegisterByToken(RegisterPasswordRepository repository) {
        this.repository = repository;
    }

    public RegisterPassword getEntityOrThrow(String token){
        Optional<RegisterPassword> entity=repository.findRegisterByToken(token);
        if(entity.isPresent()){
            log.debug("✅ A record with token {} was successfully found on the server.",token);
            return entity.get();
        }
        log.error("❌ No record with token {} was found on the server.",token);
        throw new NotFoundException("No record was found on the server with the token passed in the request.");
    }
}
