package com.example.desafio.dto.response.received.password;

import java.time.LocalDateTime;

public class ResponseReceivedPassword{
    private String message;
    private LocalDateTime timestamp;

    public ResponseReceivedPassword(String message) {
        this.message = message;
        this.timestamp=LocalDateTime.now();
    }


    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
