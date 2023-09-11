package com.avanade.rpg.service;

import com.avanade.rpg.dto.*;
import com.avanade.rpg.entity.Character;
import com.avanade.rpg.entity.Session;
import com.avanade.rpg.repository.CharacterRepository;
import com.avanade.rpg.repository.SessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class SessionService {
    private final SessionRepository sessionRepository;
    private final CharacterRepository characterRepository;

    public SessionService(SessionRepository sessionRepository, CharacterRepository characterRepository) {
        this.sessionRepository = sessionRepository;
        this.characterRepository = characterRepository;
    }

    private boolean checkIfCharacterExistsById(Long id){
        return characterRepository.existsById(id);
    }

    private boolean checkIfEnemyIsMonster(Long id){
        Optional<Character> character = characterRepository.findById(id);
        CharacterType type = character.get().getCharacterType();
        return type == CharacterType.MONSTER;
    }

    private String createSessionUuid(){
        return UUID.randomUUID().toString();
    }

    private Character retrieveCharacterById(Long id){
        Optional<Character> character = characterRepository.findById(id);
        return character.get();
    }

    private Session retrieveSessionByUuid(String session_id){
        Session session = sessionRepository.findSessionBySessionId(session_id);
        return session;
    }

    private Optional<Character> retrieveRandomMonster(){
        List<Character> characterList = characterRepository.findAll();
        return characterList.stream()
                .filter(character -> character.getCharacterType() == CharacterType.MONSTER)
                .findFirst();

    }

    public int rollDice(int numRolls, int numFaces){
        Random random = new Random();
        int result = 0;

        for (int i = 0; i < numRolls; i++) {
            int roll = random.nextInt(numFaces);
            result += roll;
        }

        return result;
    }

    private SessionTeamEnum retrieveCharacterTeam(Long id){
        SessionTeamEnum characterTeam = sessionRepository.findTeamByCharacterId(id);
        return characterTeam;
    }

    public SessionResponseDto createSession(SessionRequestDto requestDto){
        Long allyId = requestDto.ally_id();
        Long enemyId = requestDto.enemy_id();

        if(enemyId == null){
            Optional<Character> character = retrieveRandomMonster();
            enemyId = character.get().getId();
        }

        boolean allyExists = checkIfCharacterExistsById(allyId);
        boolean enemyExists = checkIfCharacterExistsById(enemyId);

        if(allyExists && enemyExists){
            boolean isEnemyMonster = checkIfEnemyIsMonster(enemyId);
            if(isEnemyMonster){
                Character characterEnemy = retrieveCharacterById(enemyId);
                Character characterAlly = retrieveCharacterById(allyId);
                String sessionId = createSessionUuid();

                int allyRoll;
                int enemyRoll;
                do {
                    allyRoll = rollDice(1, 20);
                    enemyRoll = rollDice(1, 20);
                } while (allyRoll == enemyRoll);

                SessionTeamEnum currentTurn = (allyRoll > enemyRoll) ? SessionTeamEnum.ALLY : SessionTeamEnum.ENEMY;

                Session session = new Session(characterAlly.getHealthPoints(), characterEnemy.getHealthPoints(),
                        sessionId, allyRoll, enemyRoll, currentTurn, characterAlly, characterEnemy, 0, false);

                this.sessionRepository.save(session);

                return session.SessionToResponseDto();
            }
            return null;
        }
        return null;
    }

    public String playTurn(TurnRequestDto turnRequestDto){
        Session session = retrieveSessionByUuid(turnRequestDto.session_id());
        Long attackerId = turnRequestDto.attacker_id();
        Long defenderId = turnRequestDto.defender_id();

        if(defenderId == attackerId){

        }

        Character characterAttacker = retrieveCharacterById(attackerId);
        Character characterDefender = retrieveCharacterById(defenderId);


        SessionTeamEnum currentAttackerTeam = retrieveCharacterTeam(attackerId);
        SessionTeamEnum currentTurnTeam = session.getCurrentTurn();

        if(currentTurnTeam.equals(currentAttackerTeam)){
            if(!session.isSessionOver()){

            }
        }


//        int currentTurnCount = session.getTurnCount();
//        session.setTurnCount(currentTurnCount + 1);
//        sessionRepository.save(session);
        return "hey";
    }
}
