package com.avanade.rpg.entity;

import com.avanade.rpg.dto.CharacterDto;
import com.avanade.rpg.dto.CharacterType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "characters")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private CharacterType characterType;

    private String name;

    private int healthPoints;

    private int strength;

    private int defense;

    private int agility;

    private int diceTimesRoll;

    private int diceFaces;

    public Character(CharacterDto characterDto){
        this.characterType = characterDto.character_type();
        this.name = characterDto.name();
        this.healthPoints = characterDto.health_points();
        this.strength = characterDto.strength();
        this.defense = characterDto.defense();
        this.agility = characterDto.agility();
        this.diceTimesRoll = characterDto.dice_times_roll();
        this.diceTimesRoll = characterDto.dice_faces();
    }

    public Character(CharacterDto characterDto, Long id){
        this(characterDto);
        this.id = id;
    }

    public CharacterDto CharacterToDto(){
        return new CharacterDto(this.id, this.characterType, this.name, this.healthPoints, this.strength, this.defense,
                this.agility, this.diceTimesRoll, this.diceTimesRoll);
    }



}
