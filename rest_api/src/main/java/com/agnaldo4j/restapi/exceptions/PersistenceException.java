package com.agnaldo4j.restapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class PersistenceException extends RuntimeException {
    public PersistenceException() {
        super();
    }

    public PersistenceException(String message) {
        super(message);
    }
}
