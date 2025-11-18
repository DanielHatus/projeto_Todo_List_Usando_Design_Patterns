package com.example.desafio.dto.response.validation.password;

import java.time.LocalDateTime;

public class ResponseValidationPassword {
    private String message;
    private LocalDateTime timestamp;

    public ResponseValidationPassword(String message){
        this.message=message;
        this.timestamp=LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
