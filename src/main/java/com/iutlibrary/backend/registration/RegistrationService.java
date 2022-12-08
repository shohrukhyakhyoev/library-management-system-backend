package com.iutlibrary.backend.registration;


import com.iutlibrary.backend.appUserDetails.Account;
import com.iutlibrary.backend.appUserDetails.AccountService;
import com.iutlibrary.backend.exception.ApiRequestException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Serves as a service layer for requests associated with registration process.
 */
@Service
@AllArgsConstructor
public class RegistrationService {

    /**
     * @field emailValidator validates each email coming from the registration request.
     * @field accountService used to interact with an account service.
     */
    private final EmailValidator emailValidator;
    private final AccountService accountService;

    /**
     * Registers new app user to the system.
     * It firstly validates an email. Once it is valid, system calls
     * sign up method of an account service. Otherwise, it throws an error message
     * saying 'email is not valid'.
     * @param request
     * @return
     */
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
