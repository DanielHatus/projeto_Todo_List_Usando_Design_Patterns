package com.example.desafio.repository.register.password.project;

import com.example.desafio.model.register.token.password.project.RegisterPasswordProject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegisterProjectPasswordRepository extends JpaRepository<RegisterPasswordProject,Long>{
    boolean existsByToken(String token);
    Optional<RegisterPasswordProject> findByToken(String token);
}
