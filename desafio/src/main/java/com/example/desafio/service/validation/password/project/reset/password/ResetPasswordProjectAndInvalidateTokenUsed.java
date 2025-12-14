package com.example.desafio.service.validation.password.project.reset.password;

import com.example.desafio.dto.response.validation.password.ResponseValidationPassword;
import com.example.desafio.model.project.Project;
import com.example.desafio.model.register.password.project.RegisterPasswordProject;
import com.example.desafio.repository.project.ProjectRepository;
import com.example.desafio.service.validation.password.project.invalidate.register.InvalidateRegisterProjectAfterTokenUsed;
import com.example.desafio.utils.encryptedpassword.EncryptedPassword;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ResetPasswordProjectAndInvalidateTokenUsed{
    private final ProjectRepository repository;
    private final EncryptedPassword encryptedPassword;
    private final InvalidateRegisterProjectAfterTokenUsed invalidateRegister;

    public ResetPasswordProjectAndInvalidateTokenUsed(
            EncryptedPassword encryptedPassword,
            ProjectRepository repository,
            InvalidateRegisterProjectAfterTokenUsed invalidateRegister) {

        this.encryptedPassword = encryptedPassword;
        this.repository=repository;
        this.invalidateRegister=invalidateRegister;
    }

    public ResponseValidationPassword execute(RegisterPasswordProject registerEntity, String newPassword){
        Project entity=registerEntity.getProject();
        log.debug("✅ The entity with ID {} in the register table was successfully retrieved.",entity.getId());

        entity.setPasswordAccess(encryptedPassword.encrypted(newPassword));

        repository.save(entity);
        log.debug("✅ The password passed in the request was successfully saved on the server.");

        invalidateRegister.execute(registerEntity);

        log.debug("✅ The entire password renewal process was successful.");
        return new ResponseValidationPassword("The password has been successfully renewed.");
    }
}
