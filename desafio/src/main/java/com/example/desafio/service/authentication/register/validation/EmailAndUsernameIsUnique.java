package com.example.desafio.service.authentication.register.validation;

import com.example.desafio.exceptions.typo.runtime.badrequest.BadRequestException;
import com.example.desafio.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmailAndUsernameIsUnique{
    private final UserRepository repository;

    public EmailAndUsernameIsUnique(UserRepository repository) {
        this.repository = repository;
    }

    public void validationIfEmailAndUsernameIsUnique(String email, String username){
        if (repository.existsByEmail(email)){
            log.error("❌ There was an error registering the user because a user with this email address already exists.");
            throw new BadRequestException("Sorry, but there is already a user registered with this email address.");
        }
        if (repository.existsByUsername(username)){
            log.error("❌ There was an error registering the user because a user with this username already exists.");
            throw new BadRequestException("Sorry, but there is already a user registered with this username.");
        }
        log.debug("✅ The validation was successful without errors. The email and username are unique to this user.");
        return;
    }
}
