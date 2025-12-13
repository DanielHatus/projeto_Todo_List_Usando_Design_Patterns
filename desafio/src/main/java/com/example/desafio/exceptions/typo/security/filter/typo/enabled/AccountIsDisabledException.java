package com.example.desafio.exceptions.typo.security.filter.typo.enabled;

import org.springframework.security.core.AuthenticationException;

public class AccountIsDisabledException extends AuthenticationException{
    public AccountIsDisabledException(String message) {
        super(message);
    }
}
