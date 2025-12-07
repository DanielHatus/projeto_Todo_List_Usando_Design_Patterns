package com.example.desafio.service.received.password.project.save.register;

import com.example.desafio.model.project.Project;
import com.example.desafio.model.register.password.project.RegisterPasswordProject;
import com.example.desafio.repository.register.password.project.RegisterProjectPasswordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SaveRegisterProjectPasswordService {
    private final RegisterProjectPasswordRepository repository;

    public SaveRegisterProjectPasswordService(RegisterProjectPasswordRepository repository) {
        this.repository = repository;
    }

    public void executeSave(String token, Project entity){
        RegisterPasswordProject registerPasswordProject=new RegisterPasswordProject(); registerPasswordProject.setProject(entity);
        registerPasswordProject.setToken(token);
        repository.save(registerPasswordProject);
        log.debug("✅ The project register was successfully saved to the database.");
    }

}
