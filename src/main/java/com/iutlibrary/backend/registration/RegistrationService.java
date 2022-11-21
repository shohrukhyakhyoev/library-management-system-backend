package com.iutlibrary.backend.registration;


import com.iutlibrary.backend.appUserDetails.Account;
import com.iutlibrary.backend.appUserDetails.AccountService;
import com.iutlibrary.backend.exception.ApiRequestException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final EmailValidator emailValidator;
    private final AccountService accountService;

    public ResponseEntity<Object> register(RegistrationRequest request) {

        if (!emailValidator.isValid(request.getEmail())){
            throw new ApiRequestException("email not valid");
        }
        return accountService.singUpUser(
                new Account(
                        request.getMemberId(),
                        request.getPassword(),
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getRole()));
    }
}
