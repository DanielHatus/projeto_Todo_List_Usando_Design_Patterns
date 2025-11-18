package com.example.desafio.service.received.password.token;

import com.example.desafio.repository.register.password.RegisterPasswordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
@Slf4j
public class GenerateTokenPasswordRecovery {


    private final RegisterPasswordRepository repository;
    private final SecureRandom random=new SecureRandom();

    public GenerateTokenPasswordRecovery(RegisterPasswordRepository repository) {
        this.repository = repository;
    }

    public String execute() {
        return generateUniqueToken();
    }

    private String generateUniqueToken() {
     String token;
     int count=1;
     token=generateSevenDigitSRandom();
       while (repository.existsByToken(token)){
           token=generateSevenDigitSRandom();
           count++;
       }
       log.debug("✅ The token was only successfully generated after {} attempts.",count);
       return token;
    }

    private String generateSevenDigitSRandom() {
        StringBuilder stringBuilder=new StringBuilder();
        while (stringBuilder.length()!=7){
            stringBuilder.append(this.random.nextInt(0,10));
        }
        return stringBuilder.toString();
    }
}


