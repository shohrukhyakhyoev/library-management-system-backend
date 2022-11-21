package com.iutlibrary.backend.exception;


import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ApiRequestException extends RuntimeException {
    public ApiRequestException(String message) {
        super(message);
    }
    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
