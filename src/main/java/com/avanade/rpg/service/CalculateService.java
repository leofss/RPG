package com.avanade.rpg.service;

import com.avanade.rpg.dto.CalculateResponseDto;
import com.avanade.rpg.entity.Character;
import com.avanade.rpg.exceptions.EntityNotFoundException;
import com.avanade.rpg.repository.CharacterRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CalculateService {
    private final CharacterRepository characterRepository;
    private final SessionService sessionService;

    public CalculateService(CharacterRepository characterRepository, SessionService sessionService) {
        this.characterRepository = characterRepository;
        this.sessionService = sessionService;
    }

    public CalculateResponseDto calculateDefense(Long id){
        Optional<Character> character = characterRepository.findById(id);
        if(character.isPresent()){
            int rollTwelve = sessionService.rollDice(1, 12);
            int defense = character.get().getDefense();
            int agility = character.get().getAgility();
            int result = rollTwelve + defense + agility;
            return new CalculateResponseDto(result);
        }else {
            throw new EntityNotFoundException("Character with id " + id + " not found");
        }
    }

    public CalculateResponseDto calculateAttack(Long id){
        Optional<Character> character = characterRepository.findById(id);
        if(character.isPresent()){
            int rollTwelve = sessionService.rollDice(1, 12);
            int strength = character.get().getStrength();
            int agility = character.get().getAgility();
            int result = rollTwelve + strength + agility;
            return new CalculateResponseDto(result);
        }else {
            throw new EntityNotFoundException("Character with id " + id + " not found");
        }
    }

    public CalculateResponseDto calculateDamage(Long id){
        Optional<Character> character = characterRepository.findById(id);
        if(character.isPresent()){
            int timesRollDice = character.get().getDiceTimesRoll();
            int facesDice = character.get().getDiceFaces();
            int strength = character.get().getStrength();
            int resultDice = sessionService.rollDice(timesRollDice, facesDice);
            int result = resultDice + strength;
            return new CalculateResponseDto(result);
        }else {
            throw new EntityNotFoundException("Character with id " + id + " not found");
        }
    }
}
