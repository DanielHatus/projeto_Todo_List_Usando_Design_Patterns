package com.example.desafio.exceptions.typo.security.filter.typo.token.invalid;

import org.springframework.security.core.AuthenticationException;

public class TokenInvalid extends AuthenticationException{
    public TokenInvalid(String message) {
        super(message);
    }
}
