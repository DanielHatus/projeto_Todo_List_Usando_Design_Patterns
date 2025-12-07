package com.example.desafio.facade.validation.password;

import com.example.desafio.dto.request.validation.password.user.ValidationUserPasswordTokenDto;
import com.example.desafio.dto.response.validation.password.ResponseValidationPassword;
import com.example.desafio.model.register.password.user.RegisterPasswordUser;
import com.example.desafio.service.validation.password.user.renew.password.MakingRecoverPasswordService;
import com.example.desafio.service.validation.password.user.utils.get.GetRegisterByToken;
import com.example.desafio.service.validation.password.user.validation.TokenPasswordIsExpired;
import com.example.desafio.service.validation.password.user.validation.TokenPasswordIsUsed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ValidationTokenAndRenewPasswordFacade {
    private final GetRegisterByToken getRegisterByToken;
    private final TokenPasswordIsUsed tokenPasswordIsUsed;
    private final TokenPasswordIsExpired tokenPasswordIsExpired;
    private final MakingRecoverPasswordService makingRecoverPasswordService;

    public ValidationTokenAndRenewPasswordFacade(
            GetRegisterByToken getRegisterByToken,
            TokenPasswordIsUsed tokenPasswordIsUsed,
            TokenPasswordIsExpired tokenPasswordIsExpired,
            MakingRecoverPasswordService makingRecoverPasswordService){

        this.getRegisterByToken = getRegisterByToken;
        this.tokenPasswordIsUsed = tokenPasswordIsUsed;
        this.tokenPasswordIsExpired = tokenPasswordIsExpired;
        this.makingRecoverPasswordService=makingRecoverPasswordService;
    }



    public ResponseValidationPassword execute(ValidationUserPasswordTokenDto validationUserPasswordTokenDto){
    RegisterPasswordUser entity=getRegisterByToken.getEntityOrThrow(validationUserPasswordTokenDto.getToken());

    tokenPasswordIsExpired.ifExpiredReturnThrow(entity.getExpiresAt());

    tokenPasswordIsUsed.ifUsedReturnThrow(entity.isUsed(), validationUserPasswordTokenDto.getToken());

    return makingRecoverPasswordService.recoverPassword(entity, validationUserPasswordTokenDto.getNewPassword());
    }

}
