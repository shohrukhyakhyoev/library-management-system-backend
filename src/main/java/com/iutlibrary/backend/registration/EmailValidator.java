package com.iutlibrary.backend.registration;

import org.springframework.context.annotation.Configuration;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents an email validator.
 */
@Configuration
public class EmailValidator {

    /**
     * @field EMAIL_PATTERN represents characters not allowed to be used in the email.
     */
    private static final String EMAIL_PATTERN =  "^(.+)@(\\S+)$";

    /**
     * Checks whether an email is valid or not.
     * It returns a Boolean value after the checking process.
     *
     * @param email represents an email of a student.
     * @return Boolean value.
     */
    public boolean isValid(String email){
        return (EmailValidator.patternMatches(email, EMAIL_PATTERN));
    }

    /**
     * Configures everything needed for checking an email.
     * Then it matches email submitted from the user to not allowed email characters.
     * It returns a true value, if no non-allowed characters are found while matching.
     * Otherwise, it returns a false boolean value.
     * @param emailAddress represents an email of a student.
     * @param regexPattern represents a regex pattern.
     * @return Boolean value.
     */
    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

}
