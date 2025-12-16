package com.example.desafio.utils.validation.user.is.creator.project;

import com.example.desafio.exceptions.typo.runtime.badrequest.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserRequestIsCreatorProject{

    public void throwIfUserRequestNotCreatorProject(String usernameRequest,String usernameCreatorProject){
        if (!usernameCreatorProject.equalsIgnoreCase(usernameRequest)){
            log.debug("❌ The user is not the project creator, therefore they cannot update the project data.");
            throw new BadRequestException("Only the project creator can make updates and change project-related data.");
        }
    }
}
