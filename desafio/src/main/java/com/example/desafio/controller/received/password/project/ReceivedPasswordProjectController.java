package com.example.desafio.controller.received.password.project;

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
@RequestMapping("/api/recover/password")
public class ReceivedPasswordProjectController{
    private final SendGmailAndSaveProjectRegisterDataFacade sendGmailAndSaveProjectRegisterDataFacade;

    public ReceivedPasswordProjectController(SendGmailAndSaveProjectRegisterDataFacade sendGmailAndSaveProjectRegisterDataFacade) {
        this.sendGmailAndSaveProjectRegisterDataFacade = sendGmailAndSaveProjectRegisterDataFacade;
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping(value = "/project",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseReceivedPassword> sendEmailAndSaveRegisterTokenPasswordProject(
          @RequestBody IdProjectTokenPasswordRecoveryDto idProjectTokenPasswordRecoveryDto) throws MessagingException {

          return ResponseEntity.ok(sendGmailAndSaveProjectRegisterDataFacade.execute(idProjectTokenPasswordRecoveryDto));
    }
}
