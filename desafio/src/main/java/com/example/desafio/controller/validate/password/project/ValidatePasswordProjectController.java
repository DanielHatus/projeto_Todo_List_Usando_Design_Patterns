package com.example.desafio.controller.validate.password.project;

import com.example.desafio.docs.validate.password.project.ValidationTokenPasswordProjectDoc;
import com.example.desafio.dto.request.validation.password.ValidationTokenFromResetPasswordDto;
import com.example.desafio.dto.response.validation.password.ResponseValidationPassword;
import com.example.desafio.facade.validation.password.project.ValidationTokenPasswordFromResetPasswordProjectFacade;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/project/validate/password")
public class ValidatePasswordProjectController implements ValidationTokenPasswordProjectDoc {
    private final ValidationTokenPasswordFromResetPasswordProjectFacade validationTokenPasswordFromResetPasswordProjectFacade;


    public ValidatePasswordProjectController(ValidationTokenPasswordFromResetPasswordProjectFacade validationTokenPasswordFromResetPasswordProjectFacade) {
        this.validationTokenPasswordFromResetPasswordProjectFacade = validationTokenPasswordFromResetPasswordProjectFacade;
    }

    @Override
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseValidationPassword> validateTokenPasswordAndRenewPasswordFromResetPasswordFromProject(ValidationTokenFromResetPasswordDto validationTokenFromResetPasswordDto) {
        return ResponseEntity.ok(validationTokenPasswordFromResetPasswordProjectFacade.execute(validationTokenFromResetPasswordDto));
    }
}
