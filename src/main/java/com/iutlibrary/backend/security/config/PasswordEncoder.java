package com.iutlibrary.backend.security.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Represents a password encoder.
 */
@Configuration
public class PasswordEncoder {

    /**
     * Provides a password encoder from the BCryptPasswordEncoder that comes from the Spring Security Framework.
     * @return BCryptPasswordEncoder object.
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
