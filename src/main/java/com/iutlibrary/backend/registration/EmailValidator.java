package com.iutlibrary.backend.registration;

import org.springframework.context.annotation.Configuration;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Configuration
public class EmailValidator {

    private static final String EMAIL_PATTERN =  "^(.+)@(\\S+)$";

    public boolean isValid(String email){
        return (EmailValidator.patternMatches(email, EMAIL_PATTERN));
    }

    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

}
