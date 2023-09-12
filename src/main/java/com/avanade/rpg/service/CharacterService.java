package com.avanade.rpg.service;

import com.avanade.rpg.dto.CharacterDto;
import com.avanade.rpg.entity.Character;
import com.avanade.rpg.repository.CharacterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterService {
    private final CharacterRepository characterRepository;

    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public List<CharacterDto> getAllCharacters(){
        return this.characterRepository.findAll().stream()
                .map(Character::CharacterToDto)
                .toList();
    }

    public CharacterDto createCharacter(CharacterDto characterDto){
        Character character = new Character(characterDto);
        character = this.characterRepository.save(character);
        return character.CharacterToDto();
    }

    public CharacterDto getOneCharacter(Long id){
        Optional<Character> character = this.characterRepository.findById(id);
        return character.get().CharacterToDto();
    }

    public CharacterDto editCharacter(CharacterDto characterDto, Long id){
        Character character = new Character(characterDto, id);
        character = this.characterRepository.save(character);
        return character.CharacterToDto();
    }

    public void deleteCharacter(Long id){
        this.characterRepository.deleteById(id);
    }


}
