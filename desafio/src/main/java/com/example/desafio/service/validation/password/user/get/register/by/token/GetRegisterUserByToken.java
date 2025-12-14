package com.example.desafio.service.validation.password.user.get.register.by.token;

import com.example.desafio.exceptions.typo.runtime.notfound.NotFoundException;
import com.example.desafio.model.register.password.user.RegisterPasswordUser;
import com.example.desafio.repository.register.password.user.RegisterUserPasswordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class GetRegisterUserByToken {
    private final RegisterUserPasswordRepository repository;

    public GetRegisterUserByToken(RegisterUserPasswordRepository repository) {
        this.repository = repository;
    }

    public RegisterPasswordUser getRegisterUserOrThrow(String token){
        Optional<RegisterPasswordUser> entity=repository.findRegisterByToken(token);
        if(entity.isPresent()){
            log.debug("✅ A register with token {} was successfully found on the server.",token);
            return entity.get();
        }
        log.error("❌ No register with token {} was found on the server.",token);
        throw new NotFoundException("No register was found on the server with the token passed in the request.");
    }
}
