package com.example.desafio.controller.received.password.user;

import com.example.desafio.docs.received.password.user.ReceivedPasswordUserDoc;
import com.example.desafio.dto.request.received.password.user.EmailUserRecoveryDto;
import com.example.desafio.dto.response.received.password.ResponseReceivedPassword;
import com.example.desafio.facade.received.password.user.execute.send.mail.and.register.data.SendGmailAndSaveUserRegisterDataFacade;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/recover/password")
public class ReceivedPasswordUserUserController implements ReceivedPasswordUserDoc {
    private final SendGmailAndSaveUserRegisterDataFacade sendGmailAndSaveUserRegisterDataFacade;

    public ReceivedPasswordUserUserController(SendGmailAndSaveUserRegisterDataFacade sendGmailAndSaveUserRegisterDataFacade) {
        this.sendGmailAndSaveUserRegisterDataFacade = sendGmailAndSaveUserRegisterDataFacade;
    }

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseReceivedPassword> sendEmailFromUserFromResetPasswordInUserAccount(@RequestBody @Valid EmailUserRecoveryDto emailUserRecoveryDto) throws MessagingException {
        return ResponseEntity.ok(sendGmailAndSaveUserRegisterDataFacade.execute(emailUserRecoveryDto));
    }
}
