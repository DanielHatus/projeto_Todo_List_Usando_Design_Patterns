package com.example.desafio.exceptions.handler;

import com.example.desafio.exceptions.responsegeneric.ResponseGeneric;
import com.example.desafio.exceptions.typo.runtime.forbidden.ForbiddenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ForbiddenHandler{
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ResponseGeneric> responseHandler(ForbiddenException e){
        HttpStatus status=HttpStatus.FORBIDDEN;
        ResponseGeneric responseGeneric=new ResponseGeneric(e.getMessage(),status.name(),status.value());
        return new ResponseEntity<>(responseGeneric,status);
    }
}
