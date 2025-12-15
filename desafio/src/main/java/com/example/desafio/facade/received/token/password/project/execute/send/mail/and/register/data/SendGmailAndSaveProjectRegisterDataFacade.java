package com.example.desafio.facade.received.token.password.project.execute.send.mail.and.register.data;

import com.example.desafio.dto.request.received.token.password.project.IdProjectTokenPasswordRecoveryDto;
import com.example.desafio.dto.response.received.token.password.ResponseReceivedPassword;
import com.example.desafio.facade.received.token.password.project.get.entity.by.id.GetEntityProjectByIdOrThrow;
import com.example.desafio.facade.received.token.password.project.get.token.unique.GetTokenProjectPasswordUnique;
import com.example.desafio.model.project.Project;
import com.example.desafio.repository.user.UserRepository;
import com.example.desafio.send.email.SendEmail;
import com.example.desafio.service.received.token.password.project.save.register.SaveRegisterProjectPasswordService;
import com.example.desafio.utils.build.message.email.html.BuildMessageInHtml;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class SendGmailAndSaveProjectRegisterDataFacade {
    private final SendEmail sendEmail;
    private final BuildMessageInHtml buildMessageInHtml;
    private final GetTokenProjectPasswordUnique getTokenProjectPasswordUnique;
    private final GetEntityProjectByIdOrThrow getEntityProjectByIdOrThrow;
    private final SaveRegisterProjectPasswordService saveRegisterProjectPasswordService;
    private final UserRepository repository;
    @Value("${spring.mail.username}")
    private String emailFrom;

    public SendGmailAndSaveProjectRegisterDataFacade(
            SendEmail sendEmail,
            BuildMessageInHtml buildMessageInHtml,
            GetTokenProjectPasswordUnique getTokenProjectPasswordUnique,
            GetEntityProjectByIdOrThrow getEntityProjectByIdOrThrow,
            SaveRegisterProjectPasswordService saveRegisterProjectPasswordService,
            UserRepository repository) {

        this.sendEmail = sendEmail;
        this.buildMessageInHtml = buildMessageInHtml;
        this.getTokenProjectPasswordUnique = getTokenProjectPasswordUnique;
        this.getEntityProjectByIdOrThrow=getEntityProjectByIdOrThrow;
        this.saveRegisterProjectPasswordService=saveRegisterProjectPasswordService;
        this.repository=repository;
    }

    @Transactional
    public ResponseReceivedPassword execute(IdProjectTokenPasswordRecoveryDto idProjectTokenPasswordRecoveryDto) throws MessagingException {

        Project entity=getEntityProjectByIdOrThrow.getEntity(idProjectTokenPasswordRecoveryDto.id());

        String tokenPasswordUnique=getTokenProjectPasswordUnique.getTokenProjectUnique();

        String emailCreatorProject=repository.findByUsername(entity.getProjectCreator()).get().getEmail();

        String bodyInHtml=buildMessageInHtml.executeBuild(tokenPasswordUnique);
        log.debug("✅ body building in html successfully");

        String subject="Token for resetting the project access password.";

        saveRegisterProjectPasswordService.executeSave(tokenPasswordUnique,entity);

        sendEmail.sendEmail(this.emailFrom,emailCreatorProject,subject,bodyInHtml);

        log.debug("✅ The entire process related to receiving the email with the token was successfully completed, " +
                "returning the response data to the client.");

        return new ResponseReceivedPassword("The token password was successfully sent to the project creator's email address.");
    }
}
