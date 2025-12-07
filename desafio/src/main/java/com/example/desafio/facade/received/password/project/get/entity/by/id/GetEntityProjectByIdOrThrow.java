package com.example.desafio.facade.received.password.project.get.entity.by.id;

import com.example.desafio.exceptions.typo.runtime.notfound.NotFoundException;
import com.example.desafio.model.project.Project;
import com.example.desafio.repository.project.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class GetEntityProjectByIdOrThrow{

    private final ProjectRepository repository;

    public GetEntityProjectByIdOrThrow(ProjectRepository repository) {
        this.repository = repository;
    }

    public Project getEntity(Long id){
        Optional<Project> possibleEntity=repository.findById(id);
        if (possibleEntity.isPresent()){
            log.debug("✅ The project entity was successfully found using the ID passed in the request.");
            return possibleEntity.get();
        }
        log.error("❌ The project entity was not found with the ID passed in the request.");
        throw new NotFoundException("No project was found matching that ID.");
    }
}
