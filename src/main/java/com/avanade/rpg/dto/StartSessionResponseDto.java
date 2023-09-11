package com.avanade.rpg.dto;

public record StartSessionResponseDto(String session_id, int ally_roll_result, int enemy_roll_result) {
    @Override
    public String session_id() {
        return session_id;
    }

    @Override
    public int ally_roll_result() {
        return ally_roll_result;
    }

    @Override
    public int enemy_roll_result() {
        return enemy_roll_result;
    }

}
