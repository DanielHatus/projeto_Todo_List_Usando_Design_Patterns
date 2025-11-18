package com.example.desafio.service.received.password.register;

import com.example.desafio.model.register.password.RegisterPassword;
import com.example.desafio.model.user.User;
import com.example.desafio.repository.register.password.RegisterPasswordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SaveRegisterPasswordService {
  private final RegisterPasswordRepository repository;

    public SaveRegisterPasswordService(RegisterPasswordRepository repository) {
        this.repository = repository;
    }

    public void executeSave(String token, User requestEmailEntity){
        log.debug("✅ Initiating the process of saving the user's records for the password recovery request.");
        RegisterPassword entity=new RegisterPassword();
        entity.setToken(token);
        entity.setUser(requestEmailEntity);
        repository.save(entity);
        log.debug("✅ Record successfully saved to the database.");
    }
}
