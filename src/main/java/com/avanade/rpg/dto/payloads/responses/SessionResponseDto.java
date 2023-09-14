package com.avanade.rpg.dto.payloads.responses;

public record SessionResponseDto(Long session_id, int ally_roll, int enemy_roll) {
    @Override
    public int ally_roll() {
        return ally_roll;
    }

    @Override
    public int enemy_roll() {
        return enemy_roll;
    }

    @Override
    public Long session_id() {
        return session_id;
    }
}
