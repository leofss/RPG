package com.avanade.rpg.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidEnemyException extends RuntimeException{
    public InvalidEnemyException(){
        super("Enemy must be a monster");
    }
}
