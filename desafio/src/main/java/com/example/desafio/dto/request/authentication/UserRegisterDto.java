package com.example.desafio.dto.request.authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRegisterDto {

    @NotBlank(message = "first name cannot be null or empty.")
    @Size(min = 4,max = 25,message = "The first name must be at least 4 characters long and cannot exceed 25 characters.")
    private String firstName;

    @NotBlank(message = "last name cannot be null or empty.")
    @Size(min = 4,max = 25,message ="The last name must be at least 4 characters long and cannot exceed 25 characters." )
    private String lastName;

    @Email(message = "Invalid email address. Please fill in the information correctly and try again!")
    private String email;

    @NotBlank(message = "username cannot be null or empty.")
    @Size(min = 4,max = 25,message = "The username must be at least 4 characters long and cannot exceed 25 characters.")
    private String username;

    @NotBlank(message = "password cannot be null or empty.")
    @Size(min = 4,max = 25,message = "The password must be at least 4 characters long and cannot exceed 25 characters.")
    private String password;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
