package com.example.desafio.exceptions.typo.security.filter.typo.token.notfound;

import org.springframework.security.core.AuthenticationException;

public class TokenNotFoundException extends AuthenticationException{
    public TokenNotFoundException(String message){
        super(message);
    }
}
