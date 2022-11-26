package com.iutlibrary.backend.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class LoginRequest {
    private final String memberId;
    private final String password;
}
