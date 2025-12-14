package com.example.desafio.controller.received.password.project;

import com.example.desafio.docs.received.password.project.ReceivedPasswordProjectDoc;
import com.example.desafio.dto.request.received.password.project.IdProjectTokenPasswordRecoveryDto;
import com.example.desafio.dto.response.received.password.ResponseReceivedPassword;
import com.example.desafio.facade.received.password.project.execute.send.mail.and.register.data.SendGmailAndSaveProjectRegisterDataFacade;
import jakarta.mail.MessagingException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/project/recover/password")
public class ReceivedPasswordProjectController implements ReceivedPasswordProjectDoc {
    private final SendGmailAndSaveProjectRegisterDataFacade sendGmailAndSaveProjectRegisterDataFacade;

    public ReceivedPasswordProjectController(SendGmailAndSaveProjectRegisterDataFacade sendGmailAndSaveProjectRegisterDataFacade) {
        this.sendGmailAndSaveProjectRegisterDataFacade = sendGmailAndSaveProjectRegisterDataFacade;
    }

    @Override
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseReceivedPassword> sendEmailFromUserFromResetPasswordInProject(
          @RequestBody IdProjectTokenPasswordRecoveryDto idProjectTokenPasswordRecoveryDto) throws MessagingException {
          return ResponseEntity.ok(sendGmailAndSaveProjectRegisterDataFacade.execute(idProjectTokenPasswordRecoveryDto));
    }
}
