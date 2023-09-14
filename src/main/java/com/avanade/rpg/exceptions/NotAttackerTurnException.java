package com.avanade.rpg.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotAttackerTurnException extends RuntimeException{
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
    public NotAttackerTurnException(){
        super("Not attacker´s turn");
    }
}
