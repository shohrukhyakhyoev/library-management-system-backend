package com.iutlibrary.backend.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Handles an ApiRequestException.
 */
@ControllerAdvice
public class ApiExceptionHandler {

    /**
     * Method is called whenever the exception is thrown. It sends as a response
     * an object of ApiException. Firstly, it creates object of it within all the data:
     * message, timestamp and httpStatus; after then it informs a client about exception
     * occurred.
     *
     * @param e ApiRequestException object.
     * @return ResponseEntity object contatining object of ApiException together with the http response status.
     */
    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e){

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ApiException apiException = new ApiException(
                                    e.getMessage(),
                                    badRequest,
                                    ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(apiException, badRequest);
    }
}
