package com.example.desafio.model.register.password;

import com.example.desafio.model.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class RegisterPassword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    private LocalDateTime expiresAt;
    private boolean used;


    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RegisterPassword(){
        this.used=false;
        this.expiresAt=LocalDateTime.now().plusMinutes(5);
    }



}
