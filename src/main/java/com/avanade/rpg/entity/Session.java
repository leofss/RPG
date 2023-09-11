package com.avanade.rpg.entity;

import com.avanade.rpg.dto.SessionResponseDto;
import com.avanade.rpg.dto.SessionTeamEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "sessions")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "character_id")
    private Character character;

    private int current_health_points;

    private SessionTeamEnum team;

    private String session_id;

    private int ally_roll_number;

    private int enemy_roll_number;
    public Session(Character character, int current_health_points, SessionTeamEnum team, String session_id,
                   int ally_roll_number, int enemy_roll_number) {
        this.character = character;
        this.current_health_points = current_health_points;
        this.team = team;
        this.session_id = session_id;
        this.ally_roll_number = ally_roll_number;
        this.enemy_roll_number = enemy_roll_number;
    }

    public SessionResponseDto SessionToResponseDto(){
        return new SessionResponseDto(this.session_id);
    }
}
