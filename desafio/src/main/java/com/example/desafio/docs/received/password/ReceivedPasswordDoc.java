package com.example.desafio.docs.received.password;

import com.example.desafio.dto.request.received.password.EmailUserRecoveryDto;
import com.example.desafio.dto.response.received.password.ResponseReceivedPassword;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;

@Tag(name = "Send an email to recover your password.",
description = "This controller is responsible for sending the email with the password reset token to the user, " +
        "and also for validating the token sent by the user. This checks if the token is valid, if the user hasn't entered the wrong " +
        "token three times, and if the user hasn't been temporarily banned for exceeding the number of times the token can be " +
        "requested (three times is the maximum limit before receiving a short 5-minute ban, during which another password reset " +
        "request can be made).")
public interface ReceivedPasswordDoc {

    @Operation(
            summary ="Send an email to recover your password."
            ,description ="This method is responsible for sending the email containing the token to the user.",
            responses = {
                    @ApiResponse(responseCode = "200",description = "The email was sent successfully.")
            }

    )
    public ResponseEntity<ResponseReceivedPassword> sendEmail(EmailUserRecoveryDto emailUserRecoveryDto) throws MessagingException;
}
