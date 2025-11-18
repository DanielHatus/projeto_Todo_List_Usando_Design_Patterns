package com.example.desafio.facade.validation.password;

import com.example.desafio.dto.request.validation.password.ValidationPasswordTokenDto;
import com.example.desafio.dto.response.validation.password.ResponseValidationPassword;
import com.example.desafio.model.register.password.RegisterPassword;
import com.example.desafio.service.validation.password.renew.password.MakingRecoverPasswordService;
import com.example.desafio.service.validation.password.utils.get.GetRegisterByToken;
import com.example.desafio.service.validation.password.validation.TokenPasswordIsExpired;
import com.example.desafio.service.validation.password.validation.TokenPasswordIsUsed;
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



    public ResponseValidationPassword execute(ValidationPasswordTokenDto validationPasswordTokenDto){
    RegisterPassword entity=getRegisterByToken.getEntityOrThrow(validationPasswordTokenDto.getToken());

    tokenPasswordIsExpired.ifExpiredReturnThrow(entity.getExpiresAt());

    tokenPasswordIsUsed.ifUsedReturnThrow(entity.isUsed(), validationPasswordTokenDto.getToken());

    return makingRecoverPasswordService.recoverPassword(entity, validationPasswordTokenDto.getNewPassword());
    }

}
