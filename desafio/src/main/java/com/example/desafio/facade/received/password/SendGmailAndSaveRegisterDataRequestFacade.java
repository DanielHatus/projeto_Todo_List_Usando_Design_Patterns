package com.example.desafio.facade.received.password;

import com.example.desafio.dto.request.received.password.EmailUserRecoveryDto;
import com.example.desafio.dto.response.received.password.ResponseReceivedPassword;
import com.example.desafio.model.user.User;
import com.example.desafio.service.received.password.mail.body.build.html.BuildBodyInHtml;
import com.example.desafio.service.received.password.mail.send.SendMail;
import com.example.desafio.service.received.password.register.SaveRegisterPasswordService;
import com.example.desafio.service.received.password.token.GenerateTokenPasswordRecovery;
import com.example.desafio.service.received.password.validation.ValidationEmailRecoverRequest;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class SendGmailAndSaveRegisterDataRequestFacade {
    private final SendMail sendMail;
    private final BuildBodyInHtml buildBodyInHtml;
    private final GenerateTokenPasswordRecovery generateTokenPasswordRecovery;
    private final SaveRegisterPasswordService saveRegisterPasswordService;
    private final ValidationEmailRecoverRequest validationEmailRecoverRequest;

    public SendGmailAndSaveRegisterDataRequestFacade(
         SendMail sendMail,
         BuildBodyInHtml buildBodyInHtml,
         GenerateTokenPasswordRecovery generateTokenPasswordRecovery,
         SaveRegisterPasswordService saveRegisterPasswordService,
         ValidationEmailRecoverRequest validationEmailRecoverRequest) {

        this.sendMail = sendMail;
        this.buildBodyInHtml = buildBodyInHtml;
        this.generateTokenPasswordRecovery = generateTokenPasswordRecovery;
        this.saveRegisterPasswordService = saveRegisterPasswordService;
        this.validationEmailRecoverRequest=validationEmailRecoverRequest;
    }

    @Value("${spring.mail.username}")
    private String emailFrom;


    @Transactional
    public ResponseReceivedPassword execute(EmailUserRecoveryDto emailUserRecoveryDto) throws MessagingException {

        String tokenGenerated= generateTokenPasswordRecovery.execute();
        String emailRequest=emailUserRecoveryDto.getToGmail();

        User entityByEmailRequest=validationEmailRecoverRequest.getEntityByEmailOrThrow(emailRequest);
        saveRegisterPasswordService.executeSave(tokenGenerated,entityByEmailRequest);

        String bodyHtml= buildBodyInHtml.executeBuild(tokenGenerated);
        log.debug("✅ body building in html successfully");

        String subject="password account recovery";
        return sendMail.sendEmail(emailFrom,entityByEmailRequest.getEmail(),subject,bodyHtml);

    }
}
