package com.example.desafio.utils.get.username.by.context.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class GetUsernameByContextHolder{


    public String execute(){
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        return username;
    }
}
