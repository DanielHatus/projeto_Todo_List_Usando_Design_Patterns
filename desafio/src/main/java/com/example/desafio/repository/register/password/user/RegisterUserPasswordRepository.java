package com.example.desafio.repository.register.password.user;

import com.example.desafio.model.register.password.user.RegisterPasswordUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegisterUserPasswordRepository extends JpaRepository<RegisterPasswordUser,Long>{
    public boolean existsByToken(String token);
    public Optional<RegisterPasswordUser> findRegisterByToken(String token);

}
