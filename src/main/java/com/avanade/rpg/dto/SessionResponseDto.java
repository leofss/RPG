package com.avanade.rpg.dto;

public record SessionResponseDto(String session_id) {
    @Override
    public String session_id() {
        return session_id;
    }
}
