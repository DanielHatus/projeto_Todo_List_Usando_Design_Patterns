package com.example.desafio.utils.encryptedpassword;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EncryptedPassword {

    private final PasswordEncoder passwordEncoder;

    public EncryptedPassword(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String encrypted(String passwordCommon){
        log.debug("✅ Password successfully decoded and returned.");
        return passwordEncoder.encode(passwordCommon);
    }
}
