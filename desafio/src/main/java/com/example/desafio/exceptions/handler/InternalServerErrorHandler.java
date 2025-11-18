package com.example.desafio.exceptions.handler;

import com.example.desafio.exceptions.responsegeneric.ResponseGeneric;
import com.example.desafio.exceptions.typo.runtime.internalservererror.InternalServerErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class InternalServerErrorHandler{
    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ResponseGeneric> responseException(InternalServerErrorException e){
        HttpStatus status= HttpStatus.INTERNAL_SERVER_ERROR;
        ResponseGeneric responseGeneric=new ResponseGeneric(e.getMessage(), status.name(), status.value());
        return new ResponseEntity<>(responseGeneric,status);
    }
}
