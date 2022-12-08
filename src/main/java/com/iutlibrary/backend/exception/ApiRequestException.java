package com.iutlibrary.backend.exception;


import lombok.NoArgsConstructor;

/**
 * Represents a custom exception whose object is created whenever any kind of error message must be sent to a client.
 */
@NoArgsConstructor
public class ApiRequestException extends RuntimeException {
    /**
     * Creates an ApiRequestException object.
     *
     * @param message represents message of error occurred.
     */
    public ApiRequestException(String message) {
        super(message);
    }

    /**
     * Creates an ApiRequestException object.
     * @param message represents message of error occurred.
     * @param cause represents object of Throwable class.
     */
    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
