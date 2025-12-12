package com.example.desafio.exceptions.handler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class HttpMessageNotReadableExceptionHandler{
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> responseHandler(HttpMessageNotReadableException e){
        Throwable cause=e.getCause();
        HashMap<String,String> hashMapResponse=new HashMap<>();
        HttpStatus status=HttpStatus.BAD_REQUEST;

        if (cause instanceof InvalidFormatException){
            InvalidFormatException invalid=(InvalidFormatException) cause;
            String fieldError=invalid.getPath().get(0).getFieldName();
            String fieldsEnumValid= Arrays.toString(invalid.getTargetType().getEnumConstants());
            hashMapResponse.put("error","The enum passed in the field is invalid. The only valid enums are: "+fieldsEnumValid);
            return new ResponseEntity<>(hashMapResponse,status);
        }
        return new ResponseEntity<>(Map.of("error","The JSON file was unable to convert some of the data passed in the request."),status);
    }
}
