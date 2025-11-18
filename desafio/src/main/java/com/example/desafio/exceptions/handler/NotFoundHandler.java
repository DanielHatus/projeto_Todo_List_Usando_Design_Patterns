package com.example.desafio.exceptions.handler;

import com.example.desafio.exceptions.responsegeneric.ResponseGeneric;
import com.example.desafio.exceptions.typo.runtime.notfound.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NotFoundHandler{
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseGeneric> handlerException(NotFoundException e){
        HttpStatus status=HttpStatus.NOT_FOUND;
        ResponseGeneric responseGeneric=new ResponseGeneric(e.getMessage(), status.name(),status.value());
        return new ResponseEntity<>(responseGeneric,status);
    }
}
