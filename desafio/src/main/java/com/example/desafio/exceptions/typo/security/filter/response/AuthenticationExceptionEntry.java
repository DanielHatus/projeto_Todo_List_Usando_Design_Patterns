package com.example.desafio.exceptions.typo.security.filter.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

@Component
public class AuthenticationExceptionEntry implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        Date dateAct=new Date();
        HashMap<String,Object> responseException=new HashMap<>();
        responseException.put("message",authException.getMessage());
        responseException.put("status",HttpServletResponse.SC_UNAUTHORIZED);
        responseException.put("timestamp",dateAct);

        ObjectMapper mapperJackson=new ObjectMapper();
        mapperJackson.writeValue(response.getWriter(),responseException);
    }
}
