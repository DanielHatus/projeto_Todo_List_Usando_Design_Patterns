package com.example.desafio.service.authentication.login.user;

import com.example.desafio.dto.request.authentication.UserLoginDto;
import com.example.desafio.service.authentication.implementations.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoginUserService{
    private final AuthenticationManager authenticationManager;

    public LoginUserService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public CustomUserDetails loginUser(UserLoginDto requestDataLogin){
        Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDataLogin.email(),requestDataLogin.password()));
        CustomUserDetails userDetails=(CustomUserDetails)authentication.getPrincipal();
        log.debug("✅ user {} authenticated successfully.", userDetails.getUsername());
        return userDetails;
    }


}
