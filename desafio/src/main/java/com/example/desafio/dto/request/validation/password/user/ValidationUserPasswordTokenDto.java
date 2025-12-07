package com.example.desafio.dto.request.validation.password.user;

import jakarta.validation.constraints.Size;

public class ValidationUserPasswordTokenDto {
    private String token;

    @Size(min=4,max=20,message = "The password must contain at least 4 characters, " +
            "and the maximum number of characters allowed in the password is 20.")
    private String newPassword;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
