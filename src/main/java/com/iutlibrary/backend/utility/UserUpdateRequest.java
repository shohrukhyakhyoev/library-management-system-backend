package com.iutlibrary.backend.utility;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserUpdateRequest {
    private String firstName;
    private String lastName;
    private String email;
}
