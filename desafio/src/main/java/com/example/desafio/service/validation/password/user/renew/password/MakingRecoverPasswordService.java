package com.example.desafio.service.validation.password.user.renew.password;

import com.example.desafio.dto.response.validation.password.ResponseValidationPassword;
import com.example.desafio.model.register.password.user.RegisterPasswordUser;
import com.example.desafio.model.user.User;
import com.example.desafio.repository.user.UserRepository;
import com.example.desafio.service.validation.password.user.invalidate.register.InvalidateRegisterAfterUseToken;
import com.example.desafio.utils.encryptedpassword.EncryptedPassword;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class MakingRecoverPasswordService {
    private final EncryptedPassword encryptedPassword;
    private final UserRepository userRepository;
    private final InvalidateRegisterAfterUseToken invalidateRegisterAfterUseToken;


    public MakingRecoverPasswordService(
            EncryptedPassword encryptedPassword,
            UserRepository userRepository,
            InvalidateRegisterAfterUseToken invalidateRegisterAfterUseToken){

        this.encryptedPassword = encryptedPassword;
        this.userRepository = userRepository;
        this.invalidateRegisterAfterUseToken=invalidateRegisterAfterUseToken;
       ;
    }

    public ResponseValidationPassword recoverPassword(RegisterPasswordUser registerPasswordUser, String newPassword){
        User entity= registerPasswordUser.getUser();
        log.debug("✅ The entity with ID {} in the record table was successfully retrieved.",entity.getId());

        entity.setPassword(encryptedPassword.encrypted(newPassword));

        userRepository.save(entity);
        log.debug("✅ The password passed in the request was successfully saved on the server.");

        invalidateRegisterAfterUseToken.execute(registerPasswordUser);

        log.debug("✅  The entire password renewal process was successful.");
        return new ResponseValidationPassword("The password has been successfully renewed.");
    }
}
