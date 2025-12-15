package com.example.desafio.controller.validate.token.password.project;

import com.example.desafio.docs.validate.token.password.project.ValidationTokenPasswordProjectDoc;
import com.example.desafio.dto.request.validation.token.password.ValidationTokenFromResetPasswordDto;
import com.example.desafio.dto.response.validation.token.password.ResponseValidationPassword;
import com.example.desafio.facade.validation.token.password.project.ValidationTokenPasswordFromResetPasswordProjectFacade;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/project/validate/token/reset/password")
public class ValidatePasswordProjectController implements ValidationTokenPasswordProjectDoc {
    private final ValidationTokenPasswordFromResetPasswordProjectFacade validationTokenPasswordFromResetPasswordProjectFacade;


    public ValidatePasswordProjectController(ValidationTokenPasswordFromResetPasswordProjectFacade validationTokenPasswordFromResetPasswordProjectFacade) {
        this.validationTokenPasswordFromResetPasswordProjectFacade = validationTokenPasswordFromResetPasswordProjectFacade;
    }

    @Override
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseValidationPassword> validateTokenPasswordAndRenewPasswordFromResetPasswordFromProject(@RequestBody @Valid ValidationTokenFromResetPasswordDto validationTokenFromResetPasswordDto) {
        return ResponseEntity.ok(validationTokenPasswordFromResetPasswordProjectFacade.execute(validationTokenFromResetPasswordDto));
    }
}
