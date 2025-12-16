package com.example.desafio.utils.get.username.by.context.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GetUsernameByContextHolder{


    public String execute(){
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        log.debug("✅ The username was successfully retrieved from the security context.");
        return username;
    }
}
