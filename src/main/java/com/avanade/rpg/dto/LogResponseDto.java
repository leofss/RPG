package com.avanade.rpg.dto;

import com.avanade.rpg.entity.Character;
import com.avanade.rpg.entity.Session;

public record LogResponseDto(Session session, SessionTeamEnum first_to_attack, int attack, int current_turn_count,
                             int defense, int damage_done, int current_ally_hp, int current_enemy_hp) {
    @Override
    public Session session() {
        return session;
    }

    @Override
    public SessionTeamEnum first_to_attack() {
        return first_to_attack;
    }

    @Override
    public int attack() {
        return attack;
    }

    @Override
    public int defense() {
        return defense;
    }

    @Override
    public int damage_done() {
        return damage_done;
    }

    @Override
    public int current_ally_hp() {
        return current_ally_hp;
    }

    @Override
    public int current_enemy_hp() {
        return current_enemy_hp;
    }
}
