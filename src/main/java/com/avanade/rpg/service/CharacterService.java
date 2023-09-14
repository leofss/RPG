package com.avanade.rpg.service;

import com.avanade.rpg.dto.payloads.requests.CharacterDto;
import com.avanade.rpg.entity.Character;
import com.avanade.rpg.exceptions.EntityNotFoundException;
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

    public boolean checkIfCharacterExistsById(Long id){
        boolean existsById = characterRepository.existsById(id);
        if(!existsById){
            throw new EntityNotFoundException("Character with id " + id + " not found");
        }else{
            return true;
        }
    }

    public List<CharacterDto> getAllCharacters(){
        return this.characterRepository.findAll().stream()
                .map(Character::characterToDto)
                .toList();
    }

    public CharacterDto createCharacter(CharacterDto characterDto) {
        Character character = new Character(characterDto);
        character = this.characterRepository.save(character);
        return character.characterToDto();
    }

    public CharacterDto getOneCharacter(Long id) throws EntityNotFoundException {
        checkIfCharacterExistsById(id);
        Optional<Character> character = this.characterRepository.findById(id);
        return character.get().characterToDto();

    }

    public CharacterDto editCharacter(CharacterDto characterDto, Long id) throws EntityNotFoundException{
        checkIfCharacterExistsById(id);
        Character character = new Character(characterDto, id);
        character = this.characterRepository.save(character);
        return character.characterToDto();
    }

    public void deleteCharacter(Long id){
        checkIfCharacterExistsById(id);
        this.characterRepository.deleteById(id);
    }


}
