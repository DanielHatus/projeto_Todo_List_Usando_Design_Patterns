package com.example.desafio.controller.received.password;

import com.example.desafio.docs.received.password.ReceivedPasswordDoc;
import com.example.desafio.dto.request.received.password.EmailUserRecoveryDto;
import com.example.desafio.dto.response.received.password.ResponseReceivedPassword;
import com.example.desafio.facade.received.password.SendGmailAndSaveRegisterDataRequestFacade;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recover/password")
public class ReceivedPasswordController implements ReceivedPasswordDoc {
    private final SendGmailAndSaveRegisterDataRequestFacade sendGmailAndSaveRegisterDataRequestFacade;

    public ReceivedPasswordController(SendGmailAndSaveRegisterDataRequestFacade sendGmailAndSaveRegisterDataRequestFacade) {
        this.sendGmailAndSaveRegisterDataRequestFacade = sendGmailAndSaveRegisterDataRequestFacade;
    }

    @Override
    @PostMapping(value = "/email",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseReceivedPassword> sendEmail(@RequestBody @Valid EmailUserRecoveryDto emailUserRecoveryDto) throws MessagingException {
        return ResponseEntity.ok(sendGmailAndSaveRegisterDataRequestFacade.execute(emailUserRecoveryDto));
    }
}
