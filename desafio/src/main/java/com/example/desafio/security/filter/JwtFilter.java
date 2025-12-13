package com.example.desafio.security.filter;

import com.example.desafio.exceptions.typo.security.filter.response.AuthenticationExceptionEntry;
import com.example.desafio.exceptions.typo.security.filter.typo.token.invalid.TokenInvalidException;
import com.example.desafio.exceptions.typo.security.filter.typo.token.notfound.TokenNotFoundException;
import com.example.desafio.security.token.get.email.GetEmailByPayload;
import com.example.desafio.security.token.validation.token.TokenIsValid;
import com.example.desafio.service.authentication.implementations.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class JwtFilter extends OncePerRequestFilter{
    private final TokenIsValid tokenIsValid;
    private final GetEmailByPayload getEmailByPayload;
    private final CustomUserDetailsService customUserDetailsService;
    private final AuthenticationExceptionEntry authenticationExceptionEntry;

    public JwtFilter(
            TokenIsValid tokenIsValid,
            GetEmailByPayload getEmailByPayload,
            CustomUserDetailsService customUserDetailsService,
            AuthenticationExceptionEntry authenticationExceptionEntry) {

        this.tokenIsValid = tokenIsValid;
        this.getEmailByPayload = getEmailByPayload;
        this.customUserDetailsService = customUserDetailsService;
        this.authenticationExceptionEntry = authenticationExceptionEntry;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
            String path = request.getServletPath();
            return path.startsWith("/api/auth/login") ||
                    path.startsWith("/api/auth/register") ||
                    path.startsWith("/api/user/recover/password") ||
                    //recursos do swagger openApi
                    path.startsWith("/swagger-ui") ||
                    path.startsWith("/v3/api-docs") ||
                    path.startsWith("/api-docs")||
                    path.equals("/api-docs/swagger-config") ||
                    path.startsWith("/favicon.ico") ||
                    path.startsWith("/webjars/") ||
                    path.startsWith("/css/") ||
                    path.startsWith("/js/") ||
                    path.startsWith("/images/");
        }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            String header=request.getHeader("Authorization");
            if (!headerAndTokenExistsInRequest(header)){
                log.error("❌ No header or tokens were found in the request. Therefore, " +
                        "the requesting client is not authorized to use the current endpoint.");

                throw new TokenNotFoundException("header or token not found. insert the token in header Authroization,using Bearer <space> token");
            }
            log.debug("✅ The header and tokens were successfully found.");

            String token=getTokenInHeader(header);
            if (!tokenIsValid.execute(token)){
                log.error(" ❌The token passed in the request is invalid. Either the token was passed incorrectly or it has been altered.");

                throw new TokenInvalidException("Invalid token. Please log in again and enter a new token in the header.");
            }

            saveUserInContextSecurity(token);

            filterChain.doFilter(request,response);
            log.debug("✅ The entire authentication filtering process occurred correctly. The flow of this filter will continue from now on.");
        }
        catch (AuthenticationException e){
            authenticationExceptionEntry.commence(request,response,e);
        }


    }

    private boolean headerAndTokenExistsInRequest(String header){
        return header!=null&& header.startsWith("Bearer ");
    }

    private String getTokenInHeader(String header){
        return header.replace("Bearer ","");
    }

    private void saveUserInContextSecurity(String token){
        String emailUser=getEmailByPayload.execute(token);
        log.debug("✅ The user's email address present in the payload was successfully retrieved.");

        UserDetails userDetails=customUserDetailsService.loadUserByUsername(emailUser);
        log.debug("✅ The email address passed in the token payload was found on the server, " +
                "therefore the entity was successfully returned.");

        UsernamePasswordAuthenticationToken userAuthenticated=
                new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        log.debug("✅ The usernamePasswordToken object successfully received the entity data.");

        SecurityContextHolder.getContext().setAuthentication(userAuthenticated);
        log.debug("✅ User was successfully saved in the Spring security context.");
    }
}
