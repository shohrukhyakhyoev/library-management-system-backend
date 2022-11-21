package com.iutlibrary.backend.registration;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserRegistrationController {

    private final RegistrationService registrationService;

    @PostMapping("/api/v1/registration")
    public ResponseEntity<Object> register(@RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }

}
