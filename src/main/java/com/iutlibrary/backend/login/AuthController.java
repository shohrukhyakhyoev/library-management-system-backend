package com.iutlibrary.backend.login;


import com.iutlibrary.backend.appUserDetails.Account;
import com.iutlibrary.backend.appUserDetails.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.*;

/**
 * Serves as a controller for requests associated with login of an app user.
 * It listens to requests and then calls a certain function from the service layer.
 * Each method is mapped to a certain request type.
 */
@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class AuthController {

    /**
     * @field accountService an injected data field with the type of AccountService.
     * Each method mapped to a particular client-request call a certain function from
     * service layer (AccountService) using this data field.
     *
     * @field authenticationManager authenticates each login request of an app user.
     */
    @Autowired
    private final AccountService accountService;
    @Autowired
    AuthenticationManager authenticationManager;

    /**
     * Listens to a request asking to log in a particular app user to the system.
     *
     * @param loginRequest represents a log in request containing a memberId and a password
     * of an app user.
     * @return Account object of the app user going through the log in process.
     */
    @PostMapping("/login")
    public Account authenticateUser(@RequestBody LoginRequest loginRequest){

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getMemberId(), loginRequest.getPassword()));

        return (Account) authentication.getPrincipal();
    }

}
