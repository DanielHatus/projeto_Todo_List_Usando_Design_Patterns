package com.example.desafio.docs.authetication;

import com.example.desafio.dto.request.authentication.UserLoginDto;
import com.example.desafio.dto.request.authentication.UserRegisterDto;
import com.example.desafio.dto.response.authentication.ResponseDtoTokens;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Authentication Controller",
        description = "The controller is responsible for the authentication flow for user registration and login.")
public interface AuthUserDoc {

    @Operation(
            summary = "Register a new user and generate access and refresh tokens.",
            description = "Register a new user and generate access and refresh tokens so the user is authenticated and can use routes that require token-based authentication.",
            responses = {
              @ApiResponse(responseCode = "200",description = "token access:xxxxxxxxx,token refresh:xxxxxxxxxx"),
              @ApiResponse(responseCode = "400",description = "The data used to create your user account is invalid.")
            }
    )
    public ResponseEntity<ResponseDtoTokens> registerAndGenerateTokens(UserRegisterDto userRegisterDto);

    @Operation(
            summary = "Log in the user and receive the access and refresh tokens.",
            description = "Enter your email and password to log in and receive access and refresh tokens to use in the header, and be authenticated to request protected routes based on your authority.",
            responses = {
               @ApiResponse(responseCode = "200", description = "token access:xxxxxxxxx,token refresh:xxxxxxxxxx"),
               @ApiResponse(responseCode = "401",description = "unauthorized.Invalid login details.")
            }
    )
    public ResponseEntity<ResponseDtoTokens> loginUserAndGenerateTokens(UserLoginDto userLoginDto);
}
