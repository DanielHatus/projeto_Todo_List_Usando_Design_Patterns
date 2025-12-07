package com.example.desafio.send.email;

import com.example.desafio.exceptions.typo.runtime.internalservererror.InternalServerErrorException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SendEmail {
   private final JavaMailSender mailSender;

    public SendEmail(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String emailFrom, String emailUserTo, String subject, String htmlBody) throws MessagingException {
       try{
           log.debug("✅Starting the email sending process.");

           MimeMessage mimeMessage=mailSender.createMimeMessage();
           MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
           mimeMessageHelper.setFrom(emailFrom);
           mimeMessageHelper.setTo(emailUserTo);
           mimeMessageHelper.setSubject(subject);
           mimeMessageHelper.setText(htmlBody,true);
           mailSender.send(mimeMessage);

           log.debug("✅Email process completed successfully. Preparing to send the email.");
       }
       catch (MessagingException e){
           log.error("❌ The following exception occurred while sending the email: {}",e);
           throw new InternalServerErrorException("An internal server error occurred, which is why we were unable to send the recovery email. We apologize and please try again.");
       }
    }

}
