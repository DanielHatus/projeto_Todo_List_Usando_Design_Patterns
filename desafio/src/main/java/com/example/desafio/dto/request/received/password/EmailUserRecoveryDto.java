package com.example.desafio.dto.request.received.password;

import jakarta.validation.constraints.Email;

public class EmailUserRecoveryDto {
    @Email(message = "email is invalid. please send one email valid.")
    private String toGmail;

    public String getToGmail() {
        return toGmail;
    }

    public void setToGmail(String toGmail) {
        this.toGmail = toGmail;
    }
}
