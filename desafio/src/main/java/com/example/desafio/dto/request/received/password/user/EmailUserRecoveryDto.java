package com.example.desafio.dto.request.received.password.user;

import jakarta.validation.constraints.Email;

public class EmailUserRecoveryDto {
    @Email(message = "email syntax invalid. please send one email syntax valid.")
    private String email;

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
