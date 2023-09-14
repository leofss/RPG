package com.avanade.rpg.entity;

import com.avanade.rpg.dto.LogResponseDto;
import com.avanade.rpg.dto.SessionTeamEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "logs")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;

    @ManyToOne
    @JoinColumn(name = "ally_id")
    private Character allyId;

    @ManyToOne
    @JoinColumn(name = "enemy_id")
    private Character enemyId;

    private SessionTeamEnum firstToAttack;

    private int attack;

    private int defense;

    private int damageDone;

    private int currentAllyHp;

    private int currentEnemyHp;

    public Log(Session session, Character allyId, Character enemyId, SessionTeamEnum firstToAttack, int attack,
               int defense, int damageDone, int currentAllyHp, int currentEnemyHp){
        this.session = session;
        this.allyId = allyId;
        this.enemyId = enemyId;
        this.firstToAttack = firstToAttack;
        this.attack = attack;
        this.defense = defense;
        this.damageDone = damageDone;
        this.currentAllyHp = currentAllyHp;
        this.currentEnemyHp = currentEnemyHp;
    }

    public LogResponseDto logToDto(){
        return new LogResponseDto(this.session, this.allyId, this.enemyId, this.firstToAttack, this.attack,
                this.defense, this.damageDone, this.currentAllyHp, this.currentEnemyHp);
    }


}
