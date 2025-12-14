package com.example.desafio.controller.validate.password.user;

import com.example.desafio.docs.validate.password.user.ValidationTokenPasswordUserDoc;
import com.example.desafio.dto.request.validation.password.ValidationTokenFromResetPasswordDto;
import com.example.desafio.dto.response.validation.password.ResponseValidationPassword;
import com.example.desafio.facade.validation.password.user.ValidationTokenPasswordFromResetPasswordUserFacade;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/validate/password")
public class ValidationTokenPasswordUserUserController implements ValidationTokenPasswordUserDoc {
    private final ValidationTokenPasswordFromResetPasswordUserFacade validationTokenPasswordFromResetPasswordUserFacade;

    public ValidationTokenPasswordUserUserController(ValidationTokenPasswordFromResetPasswordUserFacade validationTokenPasswordFromResetPasswordUserFacade) {
        this.validationTokenPasswordFromResetPasswordUserFacade = validationTokenPasswordFromResetPasswordUserFacade;
    }

    @Override
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseValidationPassword> validateTokenPasswordAndRenewPasswordFromResetPasswordFromUserAccount(@RequestBody @Valid ValidationTokenFromResetPasswordDto validationUserPasswordTokenDto) {
        return ResponseEntity.ok(validationTokenPasswordFromResetPasswordUserFacade.execute(validationUserPasswordTokenDto));
    }
}
