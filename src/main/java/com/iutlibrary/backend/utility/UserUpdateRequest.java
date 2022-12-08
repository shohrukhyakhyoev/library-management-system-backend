package com.iutlibrary.backend.utility;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a request body that must come from a client so that server can update app user's details.
 */
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserUpdateRequest {
    /**
     * @field memberId app user's id given from the university
     * @field firstName app user's first name
     * @field lastName app user's first name
     * @field email app user's university email
     */
    private String firstName;
    private String lastName;
    private String email;
    private String memberId;
}
