package com.avanade.rpg.dto.payloads.responses;

public record CalculateResponseDto(int result) {
    @Override
    public int result() {
        return result;
    }
}
