package com.avanade.rpg.dto;

public record CalculateResponseDto(int result) {
    @Override
    public int result() {
        return result;
    }
}
