package com.avanade.rpg.dto;


import java.util.Optional;

public record SessionRequestDto(Long ally_id, Long enemy_id ) {
    @Override
    public Long ally_id() {
        return ally_id;
    }

    @Override
    public Long enemy_id() {
        return enemy_id;
    }
}
