package com.agnaldo4j.restapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class OldTransaction extends RuntimeException {
    public OldTransaction() {
        super();
    }

    public OldTransaction(String message) {
        super(message);
    }
}
