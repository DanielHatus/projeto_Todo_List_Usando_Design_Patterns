package com.example.desafio.dto.request.crud.user.put.complete;

import com.example.desafio.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserPutDtoDataComplete {
    @NotBlank(message = "first name cannot be null or empty.")
    private String firstName;

    @NotBlank(message = "last name cannot be null or empty.")
    private String lastName;

    @NotBlank(message = "username cannot be null or empty.")
    private String username;

    @NotNull(message = "role cannot be null.")
    private Role role;

    @NotNull(message = "enabled cannot be null.")
    private Boolean enabled;

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
