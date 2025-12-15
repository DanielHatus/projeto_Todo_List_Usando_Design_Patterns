package com.example.desafio.utils.validation.user.put.them.selves;

import com.example.desafio.exceptions.typo.runtime.forbidden.ForbiddenException;
import org.springframework.stereotype.Component;

@Component
public class ValidationIfUserEffectPutThemSelves {


    public void throwIfUserRequestIsDifferentEntityUser(String usernameUserRequest,String usernameEntity){
        if (!usernameEntity.equalsIgnoreCase(usernameUserRequest)){
            throw new ForbiddenException("You cannot update the data of a user who is not yourself.");
        }
    }
}
