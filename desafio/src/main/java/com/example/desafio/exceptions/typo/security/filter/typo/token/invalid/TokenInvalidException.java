package com.example.desafio.exceptions.typo.security.filter.typo.token.invalid;

import org.springframework.security.core.AuthenticationException;

public class TokenInvalidException extends AuthenticationException{
    public TokenInvalidException(String message) {
        super(message);
    }
}
