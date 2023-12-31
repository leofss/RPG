package com.avanade.rpg.dto.payloads.requests;

public record TurnRequestDto(Long session_id, Long attacker_id, Long defender_id) {
    @Override
    public Long session_id() {
        return session_id;
    }

    @Override
    public Long attacker_id() {
        return attacker_id;
    }

    @Override
    public Long defender_id() {
        return defender_id;
    }
}
