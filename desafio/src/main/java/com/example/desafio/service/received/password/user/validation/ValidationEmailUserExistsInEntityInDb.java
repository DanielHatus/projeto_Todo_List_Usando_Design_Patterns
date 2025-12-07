package com.example.desafio.service.received.password.user.validation;

import com.example.desafio.exceptions.typo.runtime.notfound.NotFoundException;
import com.example.desafio.model.user.User;
import com.example.desafio.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class ValidationEmailUserExistsInEntityInDb {
    private final UserRepository repository;

    public ValidationEmailUserExistsInEntityInDb(UserRepository repository) {
        this.repository = repository;
    }

    public User getEntityByEmailOrThrow(String emailRequest){
        Optional<User> entity=repository.findByEmail(emailRequest);
        if (entity.isPresent()){
            log.debug("✅ User {} was found who uses the email address {}",entity.get().getUsername(),emailRequest);
            return entity.get();
        }

        log.error("❌ No user registered with email address {} was found on our server. " +
                "The user must be registered to use the password recovery feature.",emailRequest);

        throw new NotFoundException("There is no user registered with " +
                "this email address on our server. Please register first, and if you forget your password, come back here.");
    }
}
