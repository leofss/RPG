package com.avanade.rpg.dto;

import com.avanade.rpg.entity.Character;
import com.avanade.rpg.entity.Session;

public record LogResponseDto(Session session, Character ally_id, Character enemy_id, SessionTeamEnum first_to_attack,
                             int turn_count, int attack, int defense, int damage_done, int current_ally_hp,
                             int current_enemy_hp) {
    @Override
    public Session session() {
        return session;
    }

    @Override
    public Character ally_id() {
        return ally_id;
    }

    @Override
    public Character enemy_id() {
        return enemy_id;
    }

    @Override
    public SessionTeamEnum first_to_attack() {
        return first_to_attack;
    }

    @Override
    public int turn_count() {
        return turn_count;
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
