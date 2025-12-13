package com.example.desafio.dto.request.crud.user.patch;

import com.example.desafio.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserPatchDto {
    @Size(min = 4,max = 25,message = "The first name must be at least 4 characters long and cannot exceed 25 characters.")
    private String firstName;

    @Size(min = 4,max = 25,message ="The last name must be at least 4 characters long and cannot exceed 25 characters." )
    private String lastName;

    @Size(min = 4,max = 25,message = "The username must be at least 4 characters long and cannot exceed 25 characters.")
    private String username;

    private Role role;

    private boolean isEnabled;

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}
