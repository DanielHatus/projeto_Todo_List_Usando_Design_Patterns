package com.example.desafio.service.received.token.password.user.save.register;

import com.example.desafio.model.register.token.password.user.RegisterPasswordUser;
import com.example.desafio.model.user.User;
import com.example.desafio.repository.register.password.user.RegisterUserPasswordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SaveRegisterUserPasswordService {
  private final RegisterUserPasswordRepository repository;

    public SaveRegisterUserPasswordService(RegisterUserPasswordRepository repository) {
        this.repository = repository;
    }

    public void executeSave(String token, User requestEmailEntity){
        log.debug("✅ Initiating the process of saving the user's records for the password recovery request.");
        RegisterPasswordUser entity=new RegisterPasswordUser();
        entity.setToken(token);
        entity.setUser(requestEmailEntity);
        repository.save(entity);
        log.debug("✅ Record successfully saved to the database.");
    }
}
