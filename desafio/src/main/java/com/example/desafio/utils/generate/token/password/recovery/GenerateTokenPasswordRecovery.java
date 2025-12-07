package com.example.desafio.utils.generate.token.password.recovery;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
@Slf4j
public class GenerateTokenPasswordRecovery {

    private final SecureRandom random=new SecureRandom();

    public String getNineNumberGenerated() {
        return generateNineDigitSRandom();
    }


    private String generateNineDigitSRandom() {
        StringBuilder stringBuilder=new StringBuilder();
        while (stringBuilder.length()!=9){
            stringBuilder.append(this.random.nextInt(0,10));
        }
        return stringBuilder.toString();
    }
}


