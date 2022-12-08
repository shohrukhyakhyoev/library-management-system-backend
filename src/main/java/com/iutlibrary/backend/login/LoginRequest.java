package com.iutlibrary.backend.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents a log in request.
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LoginRequest {
    /**
     * @field memberId represents a member id of a student.
     * @field password represents a password of a student.
     */
    private final String memberId;
    private final String password;
}
