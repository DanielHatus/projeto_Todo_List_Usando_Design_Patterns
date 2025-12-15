package com.example.desafio.service.validation.token.password.user.reset.password;

import com.example.desafio.dto.response.validation.token.password.ResponseValidationPassword;
import com.example.desafio.model.register.token.password.user.RegisterPasswordUser;
import com.example.desafio.model.user.User;
import com.example.desafio.repository.user.UserRepository;
import com.example.desafio.service.validation.token.password.user.invalidate.register.InvalidateRegisterUserAfterTokenUsed;
import com.example.desafio.utils.encryptedpassword.EncryptedPassword;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class ResetPasswordUserAndInvalidateTokenUsed {
    private final EncryptedPassword encryptedPassword;
    private final UserRepository userRepository;
    private final InvalidateRegisterUserAfterTokenUsed invalidateRegisterUserAfterTokenUsed;


    public ResetPasswordUserAndInvalidateTokenUsed(
            EncryptedPassword encryptedPassword,
            UserRepository userRepository,
            InvalidateRegisterUserAfterTokenUsed invalidateRegisterUserAfterTokenUsed){

        this.encryptedPassword = encryptedPassword;
        this.userRepository = userRepository;
        this.invalidateRegisterUserAfterTokenUsed = invalidateRegisterUserAfterTokenUsed;
       ;
    }

    public ResponseValidationPassword execute(RegisterPasswordUser registerPasswordUser, String newPassword){
        User entity=registerPasswordUser.getUser();
        log.debug("✅ The entity with ID {} in the register table was successfully retrieved.",entity.getId());

        entity.setPassword(encryptedPassword.encrypted(newPassword));

        userRepository.save(entity);
        log.debug("✅ The password passed in the request was successfully saved on the server.");

        invalidateRegisterUserAfterTokenUsed.execute(registerPasswordUser);

        log.debug("✅ The entire password renewal process was successful.");
        return new ResponseValidationPassword("The password has been successfully renewed.");
    }
}
