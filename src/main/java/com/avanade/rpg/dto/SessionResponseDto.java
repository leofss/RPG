package com.avanade.rpg.dto;

public record SessionResponseDto(String session_id, int ally_roll, int enemy_roll) {
    @Override
    public int ally_roll() {
        return ally_roll;
    }

    @Override
    public int enemy_roll() {
        return enemy_roll;
    }

    @Override
    public String session_id() {
        return session_id;
    }
}
