package com.iutlibrary.backend.registration;

import com.iutlibrary.backend.utility.enums.AppUserRole;
import lombok.*;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private final String memberId;
    private final String firstName;
    private final String lastName;
    private final String password;
    private final String email;
    private final AppUserRole role;
}
