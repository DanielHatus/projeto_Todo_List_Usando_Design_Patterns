package com.example.desafio.exceptions.typo.security.filter.typo.token.notfound;

import org.springframework.security.core.AuthenticationException;

public class TokenNotFound extends AuthenticationException{
    public TokenNotFound(String message){
        super(message);
    }
}
