package com.example.desafio.service.crud.user.validation.put;

import com.example.desafio.exceptions.typo.runtime.badrequest.BadRequestException;
import com.example.desafio.exceptions.typo.runtime.forbidden.ForbiddenException;
import com.example.desafio.utils.get.username.by.context.security.GetUsernameByContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ValidationIfUserEffectPutThemSelves{

    private final GetUsernameByContextHolder getUsernameByContextHolder;

    public ValidationIfUserEffectPutThemSelves(GetUsernameByContextHolder getUsernameByContextHolder) {
        this.getUsernameByContextHolder = getUsernameByContextHolder;
    }

    public void validate(String usernameEntity){
       if (!usernameEntity.equalsIgnoreCase(getUsernameByContextHolder.execute())){
         throw new ForbiddenException("You cannot update the data of a user who is not yourself.");
       }
    }
}
