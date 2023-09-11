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

    private CharacterType character_type;

    private String name;

    private int health_points;

    private int strength;

    private int defense;

    private int agility;

    private int dice_times_roll;

    private int dice_faces;

    public Character(CharacterDto characterDto){
        this.character_type = characterDto.character_type();
        this.name = characterDto.name();
        this.health_points = characterDto.health_points();
        this.strength = characterDto.strength();
        this.defense = characterDto.defense();
        this.agility = characterDto.agility();
        this.dice_times_roll = characterDto.dice_times_roll();
        this.dice_faces = characterDto.dice_faces();
    }

    public Character(CharacterDto characterDto, Long id){
        this(characterDto);
        this.id = id;
    }

    public CharacterDto CharacterToDto(){
        return new CharacterDto(this.id, this.character_type, this.name, this.health_points, this.strength, this.defense,
                this.agility, this.dice_times_roll, this.dice_faces);
    }



}
