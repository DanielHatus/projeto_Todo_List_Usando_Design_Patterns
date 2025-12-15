package com.example.desafio.model.register.token.password.project;

import com.example.desafio.model.project.Project;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "register_password_projects")
public class RegisterPasswordProject{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @ManyToOne
    @JoinColumn(name = "id_project")
    private Project project;

    private LocalDateTime expiresAt;

    private boolean used;

    public RegisterPasswordProject(){
        this.expiresAt=LocalDateTime.now().plusMinutes(10);
        this.used=false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof RegisterPasswordProject that)) return false;
        return isUsed() == that.isUsed() && Objects.equals(getId(), that.getId()) && Objects.equals(getToken(), that.getToken()) && Objects.equals(getProject(), that.getProject()) && Objects.equals(getExpiresAt(), that.getExpiresAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getToken(), getProject(), getExpiresAt(), isUsed());
    }
}
