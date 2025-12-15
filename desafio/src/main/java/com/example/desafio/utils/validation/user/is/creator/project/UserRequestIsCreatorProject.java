package com.example.desafio.utils.validation.user.is.creator.project;

import com.example.desafio.exceptions.typo.runtime.badrequest.BadRequestException;
import org.springframework.stereotype.Component;

@Component
public class UserRequestIsCreatorProject{

    public void throwIfUserRequestNotCreatorProject(String usernameRequest,String usernameCreatorProject){
        if (!usernameCreatorProject.equalsIgnoreCase(usernameRequest)){
            throw new BadRequestException("Only the project creator can make updates and change project-related data.");
        }
    }
}
