package com.iutlibrary.backend.registration;

import com.iutlibrary.backend.utility.enums.AppUserRole;
import lombok.*;

/**
 * Represents a form of request that should come to the server so that a new app user
 * can be registered to the system. This request body contains all data fields required
 * for the app user registration.
 */
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    /**
     * @field memberId app user's id given from the university
     * @field password of app user to use services at the university
     * @field firstName app user's first name
     * @field lastName app user's first name
     * @field email app user's university email
     * @field role app user's specified role
     */
    private final String memberId;
    private final String firstName;
    private final String lastName;
    private final String password;
    private final String email;
    private final AppUserRole role;
}
