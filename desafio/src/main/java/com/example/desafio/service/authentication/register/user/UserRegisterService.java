package com.example.desafio.service.authentication.register.user;

import com.example.desafio.dto.request.authentication.UserRegisterDto;
import com.example.desafio.exceptions.typo.runtime.badrequest.BadRequestException;
import com.example.desafio.mapper.UserMapperCore;
import com.example.desafio.model.user.User;
import com.example.desafio.repository.user.UserRepository;
import com.example.desafio.utils.encryptedpassword.EncryptedPassword;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserRegisterService{
    private final UserRepository repository;
    private final UserMapperCore mapperCore;
    private final EncryptedPassword encryptedPassword;

    public UserRegisterService(UserRepository repository, UserMapperCore mapperCore, EncryptedPassword encryptedPassword) {
        this.repository = repository;
        this.mapperCore = mapperCore;
        this.encryptedPassword = encryptedPassword;
    }

    public User registerUser(UserRegisterDto requestRegisterData){

      validationIfEmailAndUsernameIsUnique(requestRegisterData.getEmail(),requestRegisterData.getUsername());

      User entity=mapperCore.toEntity(requestRegisterData);

      entity.setPassword(encryptedPassword.encrypted(entity.getPassword()));

      log.debug("✅ password encrypted and add in attribute successfully");
      repository.save(entity);

        log.debug("✅ user{} saved successfully in database", entity.getUsername());
      return entity;
    }

    private void validationIfEmailAndUsernameIsUnique(String email,String username){
        if (repository.existsByEmail(email)){
            log.error("❌ There was an error registering the user because a user with this email address already exists.");
            throw new BadRequestException("Sorry, but there is already a user registered with this email address.");
        }
        if (repository.existsByUsername(username)){
            log.error("❌ There was an error registering the user because a user with this username already exists.");
        }
    }
}
