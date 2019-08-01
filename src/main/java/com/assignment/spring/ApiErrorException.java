package com.assignment.spring;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * General exception used to inform errors in the API retrieving weather information
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ApiErrorException extends RuntimeException {
    public ApiErrorException(final Exception e) {
        super(e);
    }
}
