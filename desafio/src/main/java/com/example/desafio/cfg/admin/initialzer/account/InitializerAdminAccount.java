package com.example.desafio.cfg.admin.initialzer.account;

import com.example.desafio.enums.Role;
import com.example.desafio.model.user.User;
import com.example.desafio.repository.user.UserRepository;
import com.example.desafio.utils.encryptedpassword.EncryptedPassword;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class InitializerAdminAccount{
    private final UserRepository repository;
    private final EncryptedPassword encryptedPassword;

    public InitializerAdminAccount(UserRepository repository, EncryptedPassword encryptedPassword) {
        this.repository = repository;
        this.encryptedPassword = encryptedPassword;
    }

    @Bean
    public CommandLineRunner register(){
        return args -> {
            boolean existsEntity=repository.existsByUsername("admin");
            if (!existsEntity){
                log.debug("✅ admin account created successfully.");
                User user=new User();
                user.setPassword(encryptedPassword.encrypted("admin123"));
                user.setRole(Role.ADMIN);
                user.setEmail("admin@gmail.com");
                user.setUsername("admin");
                user.setFirstName("admin");
                user.setLastName("admin");
                repository.save(user);
            }
            else{
                log.debug("✅ admin account already exists in the database.");
            }

        };
    }
}
