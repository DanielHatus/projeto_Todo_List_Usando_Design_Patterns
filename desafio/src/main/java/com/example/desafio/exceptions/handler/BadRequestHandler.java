package com.example.desafio.exceptions.handler;

import com.example.desafio.exceptions.responsegeneric.ResponseGeneric;
import com.example.desafio.exceptions.typo.runtime.badrequest.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BadRequestHandler{
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ResponseGeneric> handlerException(BadRequestException e){
        HttpStatus status=HttpStatus.BAD_REQUEST;
        ResponseGeneric responseGeneric=new ResponseGeneric(e.getMessage(),status.name(), status.value());
        return new ResponseEntity<>(responseGeneric,status);
    }
}
