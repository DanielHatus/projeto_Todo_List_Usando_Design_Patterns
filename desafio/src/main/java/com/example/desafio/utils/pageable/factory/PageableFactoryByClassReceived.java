package com.example.desafio.utils.pageable.factory;

import com.example.desafio.exceptions.typo.runtime.badrequest.BadRequestException;
import com.example.desafio.exceptions.typo.runtime.internalservererror.InternalServerErrorException;
import com.example.desafio.model.project.Project;
import com.example.desafio.model.user.User;
import com.example.desafio.utils.pageable.implementations.project.ImplementPageableProject;
import com.example.desafio.utils.pageable.implementations.user.ImplementPageableUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PageableFactoryByClassReceived{
    private final ImplementPageableUser implementPageableUser;
    private final ImplementPageableProject implementPageableProject;

    public PageableFactoryByClassReceived(
            ImplementPageableUser implementPageableUser,
            ImplementPageableProject implementPageableProject) {

        this.implementPageableUser = implementPageableUser;
        this.implementPageableProject=implementPageableProject;
    }

    public<ClassReceived> Pageable pageableFactory(Class<ClassReceived> classReceived, Integer page, Integer size, String order, String direction){
        if (classReceived==User.class){
            log.debug("✅ The pageable component of the User class was created successfully.");
            return implementPageableUser.generatePageable(page,size,order,direction);
        }

        if (classReceived== Project.class){
            log.debug("✅ The pageable component of the Project class was created successfully.");
            return implementPageableProject.generatePageable(page,size,order,direction);
        }

            log.error("❌ There is no implemented pageable of the class passed as a parameter.");
            throw new InternalServerErrorException("An internal server error occurred, I'm sorry. Please try again.");
    }
}
