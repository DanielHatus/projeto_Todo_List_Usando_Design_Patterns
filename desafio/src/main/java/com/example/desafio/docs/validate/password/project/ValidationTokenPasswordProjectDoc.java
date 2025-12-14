package com.example.desafio.docs.validate.password.project;

import com.example.desafio.dto.request.validation.password.ValidationTokenFromResetPasswordDto;
import com.example.desafio.dto.response.validation.password.ResponseValidationPassword;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name="controller that validates the password recovery token"
        ,description = "The controller will receive the token generated at another endpoint. It will validate whether the token passed " +
        "actually exists, whether it has already been used, and whether it has expired.")
public interface ValidationTokenPasswordProjectDoc{

    @Operation(
            summary = "It is responsible for performing this token validation, and if everything goes correctly, " +
                    "it resets the user's password.",
            responses = {
                    @ApiResponse(responseCode = "200",description = "Password renewed successfully."),
                    @ApiResponse(responseCode = "404",description = "The token passed is incorrect because the data x " +
                            "(referencing the incorrect data) is incorrect (expired or already used).")
            }
    )
    public ResponseEntity<ResponseValidationPassword> validateTokenPasswordAndRenewPasswordFromResetPasswordFromProject(ValidationTokenFromResetPasswordDto validationTokenFromResetPasswordDto);
}
