package com.example.desafio.repository.register.password.project;

import com.example.desafio.model.register.password.project.RegisterPasswordProject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterProjectPasswordRepository extends JpaRepository<RegisterPasswordProject,Long>{
    boolean existsTokenInRegisterProject(String token);
}
