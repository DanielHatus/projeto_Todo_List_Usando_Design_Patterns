package com.example.desafio.service.authentication.implementations;

import com.example.desafio.exceptions.typo.security.filter.typo.enabled.AccountIsDisabledException;
import com.example.desafio.model.user.User;
import com.example.desafio.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class CustomUserDetailsService implements UserDetailsService{

    private UserRepository repository;

    public CustomUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       User entity=validationExistsEmailAndRetunedEntityOrThrow(email);
       validationIfAccountIsActiveAndThrowIfAccountIsDisable(entity.getEnabled());
       return new CustomUserDetails(entity);
    }

    private User validationExistsEmailAndRetunedEntityOrThrow(String email){
        Optional<User> entity=repository.findByEmail(email);
        if (entity.isPresent()){
            return entity.get();
        }

        log.error("❌ The user with email address {} was not found registered in the database.",email);
        throw new UsernameNotFoundException("email not found in database");
    }

    private void validationIfAccountIsActiveAndThrowIfAccountIsDisable(boolean enabledEntityStateless){
        if(!enabledEntityStateless){
            throw new AccountIsDisabledException("We were unable to log in because your account was banned by an administrator.");
        }
    }

}
