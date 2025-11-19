package com.example.desafio.model.user;

import com.example.desafio.enums.Role;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
    private boolean enabled;

    private LocalDate dateRegister;
    private LocalDate lastUpdate;

    public User(){
        this.enabled=true;
        this.role=Role.USER;
        this.dateRegister=LocalDate.now();
        this.lastUpdate=LocalDate.now();
    }

    @PreUpdate
    protected void updateEntity(){
        this.lastUpdate=LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role){
        this.role=role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

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

    public LocalDate getDateRegister() {
        return dateRegister;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User user)) return false;
        return isEnabled() == user.isEnabled() && Objects.equals(getId(), user.getId()) && Objects.equals(getFirstName(), user.getFirstName()) && Objects.equals(getLastName(), user.getLastName()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getPassword(), user.getPassword()) && getRole() == user.getRole() && Objects.equals(getDateRegister(), user.getDateRegister()) && Objects.equals(getLastUpdate(), user.getLastUpdate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getEmail(), getUsername(), getPassword(), getRole(), isEnabled(), getDateRegister(), getLastUpdate());
    }
}
