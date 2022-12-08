package com.iutlibrary.backend.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

/**
 * Represents a custom exception message that is sent an error message to a client.
 */
@Getter
@AllArgsConstructor
public class ApiException {
    /**
     * @field message error message that describes an exception
     * @field httpStatus represents http status of response to a client's request.
     * @field  timestamp represents time when this object is created/exception is occurred.
     */
    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;
}
