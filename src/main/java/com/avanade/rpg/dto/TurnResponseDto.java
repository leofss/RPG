package com.avanade.rpg.dto;

public record TurnResponseDto(int damage_done, int current_defender_health_points, boolean is_session_over){
    @Override
    public int damage_done() {
        return damage_done;
    }

    @Override
    public int current_defender_health_points() {
        return current_defender_health_points;
    }

    @Override
    public boolean is_session_over() {
        return is_session_over;
    }
}
