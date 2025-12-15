package com.example.desafio.facade.validation.token.password.project;

import com.example.desafio.dto.request.validation.token.password.ValidationTokenFromResetPasswordDto;
import com.example.desafio.dto.response.validation.token.password.ResponseValidationPassword;
import com.example.desafio.model.register.token.password.project.RegisterPasswordProject;
import com.example.desafio.service.validation.token.password.project.get.register.by.token.GetRegisterProjectByToken;
import com.example.desafio.service.validation.token.password.project.reset.password.ResetPasswordProjectAndInvalidateTokenUsed;
import com.example.desafio.utils.validation.token.from.reset.password.is.expired.TokenPasswordIsExpired;
import com.example.desafio.utils.validation.token.from.reset.password.is.used.TokenPasswordIsUsed;
import org.springframework.stereotype.Component;

@Component
public class ValidationTokenPasswordFromResetPasswordProjectFacade{

    private final GetRegisterProjectByToken getRegister;
    private final TokenPasswordIsUsed tokenPasswordIsUsed;
    private final TokenPasswordIsExpired tokenPasswordIsExpired;
    private final ResetPasswordProjectAndInvalidateTokenUsed resetPasswordProjectAndInvalidateTokenUsed;

    public ValidationTokenPasswordFromResetPasswordProjectFacade(
            GetRegisterProjectByToken getRegister,
            TokenPasswordIsUsed tokenPasswordIsUsed,
            TokenPasswordIsExpired tokenPasswordIsExpired,
            ResetPasswordProjectAndInvalidateTokenUsed resetPasswordProjectAndInvalidateTokenUsed) {

        this.getRegister = getRegister;
        this.tokenPasswordIsUsed = tokenPasswordIsUsed;
        this.tokenPasswordIsExpired = tokenPasswordIsExpired;
        this.resetPasswordProjectAndInvalidateTokenUsed=resetPasswordProjectAndInvalidateTokenUsed;
    }

    public ResponseValidationPassword execute(ValidationTokenFromResetPasswordDto validationTokenFromResetPasswordDto){
        RegisterPasswordProject registerEntity=getRegister.getRegisterProjectOrThrow(validationTokenFromResetPasswordDto.getToken());

        tokenPasswordIsUsed.validationIfTokenIsUsedAndThrowIfUsed(registerEntity.isUsed());

        tokenPasswordIsExpired.validationIfTokenIsExpiredAndThrowIfTokenExpired(registerEntity.getExpiresAt());

        return resetPasswordProjectAndInvalidateTokenUsed.execute(registerEntity, validationTokenFromResetPasswordDto.getNewPassword());
    }
}
