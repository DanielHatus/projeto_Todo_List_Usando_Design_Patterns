package com.example.desafio.repository.register.password;

import com.example.desafio.model.register.password.RegisterPassword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegisterPasswordRepository extends JpaRepository<RegisterPassword,Long>{
    public boolean existsByToken(String token);
    public Optional<RegisterPassword> findRegisterByToken(String token);

}
