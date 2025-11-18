package com.example.desafio.exceptions.typo.runtime.badrequest;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message){
        super(message);
    }
}
