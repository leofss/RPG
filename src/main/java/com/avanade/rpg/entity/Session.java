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

    private int currentAllyHealthPoints;

    private int currentEnemyHealthPoints;

    private String sessionId;

    private int allyRollNumber;

    private int enemyRollNumber ;

    private SessionTeamEnum currentTurn;

    @ManyToOne
    @JoinColumn(name = "ally_id")
    private Character characterAlly;

    @ManyToOne
    @JoinColumn(name = "enemy_id")
    private Character characterEnemy;

    private int turnCount;


    public Session(int currentAllyHealthPoints, int currentEnemyHealthPoints, String sessionId, int allyRollNumber,
                   int enemyRollNumber, SessionTeamEnum currentTurn, Character characterAlly, Character characterEnemy, int turnCount) {
        this.currentAllyHealthPoints = currentAllyHealthPoints;
        this.currentEnemyHealthPoints = currentEnemyHealthPoints;
        this.sessionId = sessionId;
        this.allyRollNumber = allyRollNumber;
        this.enemyRollNumber = enemyRollNumber;
        this.currentTurn = currentTurn;
        this.characterAlly = characterAlly;
        this.characterEnemy = characterEnemy;
        this.turnCount = turnCount;
    }

    public SessionResponseDto SessionToResponseDto(){
        return new SessionResponseDto(this.sessionId);
    }
}
