package com.avanade.rpg.exceptions;

public class CharacterNotFoundException extends Exception{
    public CharacterNotFoundException(Long id){
        super("Character with id" + id + "not found");
    }
}
