package com.example.desafio.dto.request.crud.user.put.simple;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserPutDtoDataSimple {


    @NotBlank(message = "first name cannot be null or empty.")
    @Size(min = 4,max = 25,message = "The first name must be at least 4 characters long and cannot exceed 25 characters.")
    private String firstName;

    @NotBlank(message = "last name cannot be null or empty.")
    @Size(min = 4,max = 25,message ="The last name must be at least 4 characters long and cannot exceed 25 characters." )
    private String lastName;

    @NotBlank(message = "username cannot be null or empty.")
    @Size(min = 4,max = 25,message = "The username must be at least 4 characters long and cannot exceed 25 characters.")
    private String username;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
