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

    private boolean isSessionOver;


    public Session(int currentAllyHealthPoints, int currentEnemyHealthPoints, int allyRollNumber, int enemyRollNumber,
                   SessionTeamEnum currentTurn, Character characterAlly, Character characterEnemy, int turnCount,
                   boolean isSessionOver) {
        this.currentAllyHealthPoints = currentAllyHealthPoints;
        this.currentEnemyHealthPoints = currentEnemyHealthPoints;
        this.allyRollNumber = allyRollNumber;
        this.enemyRollNumber = enemyRollNumber;
        this.currentTurn = currentTurn;
        this.characterAlly = characterAlly;
        this.characterEnemy = characterEnemy;
        this.turnCount = turnCount;
        this.isSessionOver = isSessionOver;
    }


    public SessionResponseDto sessionToResponseDto(){
        return new SessionResponseDto(this.id, this.allyRollNumber, this.enemyRollNumber);
    }
}
