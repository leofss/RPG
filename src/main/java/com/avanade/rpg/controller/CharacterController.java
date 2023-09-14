package com.avanade.rpg.controller;

import com.avanade.rpg.dto.payloads.requests.CharacterDto;
import com.avanade.rpg.exceptions.EntityNotFoundException;
import com.avanade.rpg.service.CharacterService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/character")
public class CharacterController {
    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping
    public List<CharacterDto> getAllCharacters(){
        return characterService.getAllCharacters();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CharacterDto createCharacter( @RequestBody CharacterDto body) {
        return characterService.createCharacter(body);
    }

    @GetMapping("/{id}")
    public CharacterDto getOneCharacter(@PathVariable Long id) throws EntityNotFoundException {
        return characterService.getOneCharacter(id);
    }

    @PutMapping("/{id}")
    public CharacterDto editCharacter(@RequestBody CharacterDto body, @PathVariable Long id){
        return characterService.editCharacter(body, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCharacter(@PathVariable Long id){
        characterService.deleteCharacter(id);
    }
}
