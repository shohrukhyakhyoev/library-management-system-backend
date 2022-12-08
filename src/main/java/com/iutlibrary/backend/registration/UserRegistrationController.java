package com.iutlibrary.backend.registration;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Serves as a controller for requests associated with registration process.
 * It listens to requests and then calls a certain function from the service layer.
 * Each method is mapped to a certain request type.
 */
@RestController
@AllArgsConstructor
public class UserRegistrationController {

    /**
     * @field registrationService an injected data field with the type of RegistrationService.
     * Each method mapped to a particular client-request call a certain function from
     * service layer (RegistrationService) using this data field.
     */
    private final RegistrationService registrationService;

    /**
     * Listens to a request asking to register a new app user to the system.
     * @param request represents a Request body containing all details of an app user
     * so that it can be registered as an app user in the system.
     * @return ReponseEntity object.
     */
    @PostMapping("/api/v1/registration")
    public ResponseEntity<Object> register(@RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }

}
