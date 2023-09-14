package com.avanade.rpg.dto;

import com.avanade.rpg.entity.Session;

public record LogResponseDto(Session session, SessionTeamEnum first_to_attack, int attack, int current_turn_count,
                             int defense, int damage_done, int current_ally_hp, int current_enemy_hp) {

}
