package com.example.desafio.facade.validation.password.user;

import com.example.desafio.dto.request.validation.password.ValidationTokenFromResetPasswordDto;
import com.example.desafio.dto.response.validation.password.ResponseValidationPassword;
import com.example.desafio.model.register.password.user.RegisterPasswordUser;
import com.example.desafio.service.validation.password.user.reset.password.ResetPasswordUserAndInvalidateTokenUsed;
import com.example.desafio.service.validation.password.user.get.register.by.token.GetRegisterUserByToken;
import com.example.desafio.utils.validation.token.from.reset.password.is.expired.TokenPasswordIsExpired;
import com.example.desafio.utils.validation.token.from.reset.password.is.used.TokenPasswordIsUsed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ValidationTokenPasswordFromResetPasswordUserFacade {
    private final GetRegisterUserByToken getRegisterUserByToken;
    private final TokenPasswordIsUsed tokenPasswordIsUsed;
    private final TokenPasswordIsExpired tokenPasswordIsExpired;
    private final ResetPasswordUserAndInvalidateTokenUsed resetPasswordUserAndInvalidateTokenUsed;

    public ValidationTokenPasswordFromResetPasswordUserFacade(
            GetRegisterUserByToken getRegisterUserByToken,
            TokenPasswordIsUsed tokenPasswordIsUsed,
            TokenPasswordIsExpired tokenPasswordIsExpired,
            ResetPasswordUserAndInvalidateTokenUsed resetPasswordUserAndInvalidateTokenUsed){

        this.getRegisterUserByToken = getRegisterUserByToken;
        this.tokenPasswordIsUsed = tokenPasswordIsUsed;
        this.tokenPasswordIsExpired = tokenPasswordIsExpired;
        this.resetPasswordUserAndInvalidateTokenUsed = resetPasswordUserAndInvalidateTokenUsed;
    }



    public ResponseValidationPassword execute(ValidationTokenFromResetPasswordDto validationUserPasswordTokenDto){
    RegisterPasswordUser registerEntity=getRegisterUserByToken.getRegisterUserOrThrow(validationUserPasswordTokenDto.getToken());

    tokenPasswordIsExpired.validationIfTokenIsExpiredAndThrowIfTokenExpired(registerEntity.getExpiresAt());

    tokenPasswordIsUsed.validationIfTokenIsUsedAndThrowIfUsed(registerEntity.isUsed());

    return resetPasswordUserAndInvalidateTokenUsed.execute(registerEntity, validationUserPasswordTokenDto.getNewPassword());
    }

}
