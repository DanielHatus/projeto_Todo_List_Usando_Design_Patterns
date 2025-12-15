package com.example.desafio.utils.validation.user.is.role.admin;

import com.example.desafio.enums.Role;
import org.springframework.stereotype.Component;

@Component
public class ValidationIfUserIsRoleAdmin{

    public boolean userIsAdmin(Role roleUser){
        return (roleUser==Role.ROLE_ADMIN?true:false);
    }
}
