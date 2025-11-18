package com.example.desafio.exceptions.typo.runtime.internalservererror;

public class InternalServerErrorException extends RuntimeException {
    public InternalServerErrorException(String message) {
        super(message);
    }
}
