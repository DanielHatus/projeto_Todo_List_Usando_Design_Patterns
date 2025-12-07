package com.example.desafio.facade.received.password.user.execute.send.mail.and.register.data;

import com.example.desafio.dto.request.received.password.user.EmailUserRecoveryDto;
import com.example.desafio.dto.response.received.password.ResponseReceivedPassword;
import com.example.desafio.facade.received.password.user.get.GetTokenUserPasswordUnique;
import com.example.desafio.model.user.User;
import com.example.desafio.utils.build.message.email.html.BuildMessageInHtml;
import com.example.desafio.send.email.SendEmail;
import com.example.desafio.service.received.password.user.save.register.SaveRegisterUserPasswordService;
import com.example.desafio.service.received.password.user.validation.ValidationEmailUserExistsInEntityInDb;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class SendGmailAndSaveUserRegisterDataFacade {
    private final SendEmail sendEmail;
    private final BuildMessageInHtml buildMessageInHtml;
    private final GetTokenUserPasswordUnique getTokenUserPasswordUnique;
    private final SaveRegisterUserPasswordService saveRegisterUserPasswordService;
    private final ValidationEmailUserExistsInEntityInDb validationEmailUserExistsInEntityInDb;

    public SendGmailAndSaveUserRegisterDataFacade(
         SendEmail sendEmail,
         BuildMessageInHtml buildMessageInHtml,
         GetTokenUserPasswordUnique getTokenUserPasswordUnique,
         SaveRegisterUserPasswordService saveRegisterUserPasswordService,
         ValidationEmailUserExistsInEntityInDb validationEmailUserExistsInEntityInDb) {

        this.sendEmail = sendEmail;
        this.buildMessageInHtml = buildMessageInHtml;
        this.getTokenUserPasswordUnique = getTokenUserPasswordUnique;
        this.saveRegisterUserPasswordService = saveRegisterUserPasswordService;
        this.validationEmailUserExistsInEntityInDb = validationEmailUserExistsInEntityInDb;
    }

    @Value("${spring.mail.username}")
    private String emailFrom;


    @Transactional
    public ResponseReceivedPassword execute(EmailUserRecoveryDto emailUserRecoveryDto) throws MessagingException {

        String tokenGenerated=getTokenUserPasswordUnique.getTokenUnique();

        String emailRequest=emailUserRecoveryDto.getEmail();

        User entityByEmailRequest=validationEmailUserExistsInEntityInDb.getEntityByEmailOrThrow(emailRequest);

        saveRegisterUserPasswordService.executeSave(tokenGenerated,entityByEmailRequest);

        String bodyHtml= buildMessageInHtml.executeBuild(tokenGenerated);
        log.debug("✅ body building in html successfully");

        String subject="password user token account recovery";
        sendEmail.sendEmail(emailFrom,entityByEmailRequest.getEmail(),subject,bodyHtml);

        log.debug("✅ The email was sent successfully.");
        return new ResponseReceivedPassword("Email sent successfully to email address "+entityByEmailRequest.getEmail());
    }


}
