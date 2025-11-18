package com.example.desafio.exceptions.responsegeneric;

import java.time.LocalDate;
import java.time.LocalTime;

public class ResponseGeneric{
    private String message;
    private String statusName;
    private int statusCode;
    private LocalDate dateRequest=LocalDate.now();
    private LocalTime timeRequest=LocalTime.now();

    public ResponseGeneric(String message, String statusName, int statusCode) {
        this.message = message;
        this.statusName = statusName;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public String getStatusName() {
        return statusName;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public LocalDate getDateRequest() {
        return dateRequest;
    }

    public LocalTime getTimeRequest() {
        return timeRequest;
    }
}
