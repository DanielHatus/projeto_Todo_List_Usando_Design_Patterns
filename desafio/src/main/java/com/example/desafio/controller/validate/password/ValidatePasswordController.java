package com.example.desafio.controller.validate.password;

import com.example.desafio.docs.validate.password.ValidatePasswordDoc;
import com.example.desafio.dto.request.validation.password.user.ValidationUserPasswordTokenDto;
import com.example.desafio.dto.response.validation.password.ResponseValidationPassword;
import com.example.desafio.facade.validation.password.ValidationTokenAndRenewPasswordFacade;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recover/password")
public class ValidatePasswordController implements ValidatePasswordDoc{
    private final ValidationTokenAndRenewPasswordFacade validationTokenAndRenewPasswordFacade;

    public ValidatePasswordController(ValidationTokenAndRenewPasswordFacade validationTokenAndRenewPasswordFacade) {
        this.validationTokenAndRenewPasswordFacade = validationTokenAndRenewPasswordFacade;
    }

    @Override
    @PostMapping(value = "/validate",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseValidationPassword> validateAndRenewPassword(@RequestBody @Valid ValidationUserPasswordTokenDto validationUserPasswordTokenDto) {
        return ResponseEntity.ok(validationTokenAndRenewPasswordFacade.execute(validationUserPasswordTokenDto));
    }
}
