package com.avanade.rpg.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException{
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
    public EntityNotFoundException(String message) {
        super(message);
    }
}
