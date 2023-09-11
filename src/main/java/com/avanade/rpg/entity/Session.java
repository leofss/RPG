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

    private int current_ally_health_points;

    private int current_enemy_health_points;

    private String session_id;

    private int ally_roll_number;

    private int enemy_roll_number;

    private SessionTeamEnum current_turn;

    @ManyToOne
    @JoinColumn(name = "ally_id")
    private Character character_ally;

    @ManyToOne
    @JoinColumn(name = "enemy_id")
    private Character character_enemy;
    public Session(int current_ally_health_points , int current_enemy_health_points, String session_id,
                   int ally_roll_number, int enemy_roll_number, Character character_ally,
                   Character character_enemy, SessionTeamEnum current_turn) {
        this.current_ally_health_points = current_ally_health_points;
        this.current_enemy_health_points = current_enemy_health_points;
        this.session_id = session_id;
        this.ally_roll_number = ally_roll_number;
        this.enemy_roll_number = enemy_roll_number;
        this.character_enemy = character_enemy;
        this.character_ally = character_ally;
        this.current_turn = current_turn;
    }

    public SessionResponseDto SessionToResponseDto(){
        return new SessionResponseDto(this.session_id);
    }
}
