package com.avanade.rpg.dto.payloads.responses;

import com.fasterxml.jackson.annotation.JsonInclude;

public record TurnResponseDto(Integer damage_done, Integer current_defender_health_points, boolean is_session_over,
                              String message){
    @Override
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Integer damage_done() {
        return damage_done;
    }

    @Override
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Integer current_defender_health_points() {
        return current_defender_health_points;
    }

    @Override
    public boolean is_session_over() {
        return is_session_over;
    }

}
