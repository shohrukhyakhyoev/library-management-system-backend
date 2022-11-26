package com.iutlibrary.backend.login;


import com.iutlibrary.backend.appUserDetails.Account;
import com.iutlibrary.backend.appUserDetails.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class AuthController {

    @Autowired
    private final AccountService accountService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public Account authenticateUser(@RequestBody LoginRequest loginRequest){

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getMemberId(), loginRequest.getPassword()));

        return (Account) authentication.getPrincipal();
    }

}
