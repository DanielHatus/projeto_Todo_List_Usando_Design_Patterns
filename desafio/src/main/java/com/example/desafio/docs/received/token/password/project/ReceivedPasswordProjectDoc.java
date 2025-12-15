package com.example.desafio.docs.received.token.password.project;

import com.example.desafio.dto.request.received.token.password.project.IdProjectTokenPasswordRecoveryDto;
import com.example.desafio.dto.response.received.token.password.ResponseReceivedPassword;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;

@Tag(name = "Send an email to recover your password.",
        description = "This controller is responsible for sending the email " +
                "with the password reset token to the user and also for validating" +
                " the token sent by the user. It checks if the token has not expired" +
                " or has already been used.")

public interface ReceivedPasswordProjectDoc{
    @Operation(
            summary ="Send an email to recover your password."
            ,description ="This method is responsible for sending the email" +
            " containing the token to the user to reset their project password.",
            responses = {
                    @ApiResponse(responseCode = "200",description = "The email was sent successfully."),
                    @ApiResponse(responseCode = "400",description = "Invalid token or password does not meet the required standards.")
            }

    )
    public ResponseEntity<ResponseReceivedPassword> sendEmailFromUserFromResetPasswordInProject(IdProjectTokenPasswordRecoveryDto idProjectTokenPasswordRecoveryDto) throws MessagingException;
}
