package com.avanade.rpg.dto.payloads.requests;

import com.avanade.rpg.dto.CharacterType;

public record CharacterDto(Long id, CharacterType character_type, String name, int health_points, int strength, int defense,
                           int agility, int dice_times_roll, int dice_faces) {
    @Override
    public String name() {
        return name;
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public CharacterType character_type() {
        return character_type;
    }

    @Override
    public int health_points() {
        return health_points;
    }

    @Override
    public int strength() {
        return strength;
    }

    @Override
    public int defense() {
        return defense;
    }

    @Override
    public int agility() {
        return agility;
    }

    @Override
    public int dice_times_roll() {
        return dice_times_roll;
    }

    @Override
    public int dice_faces() {
        return dice_faces;
    }
}
