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
        CharacterType type = character.get().getCharacter_type();
        return type == CharacterType.MONSTER;
    }

    private String createSessionUuid(){
        return UUID.randomUUID().toString();
    }

    private Character retrieveCharacterById(Long id){
        Optional<Character> character = characterRepository.findById(id);
        return character.get();
    }

    private Optional<Character> retrieveRandomMonster(){
        List<Character> characterList = characterRepository.findAll();
        return characterList.stream()
                .filter(character -> character.getCharacter_type() == CharacterType.MONSTER)
                .findFirst();

    }

    private int RollTwentyFacesDice(){
        Random random = new Random();
        return random.nextInt(20) + 1;
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
                    allyRoll = RollTwentyFacesDice();
                    enemyRoll = RollTwentyFacesDice();
                } while (allyRoll == enemyRoll);

                Session session = new Session(characterAlly.getHealth_points(), characterEnemy.getHealth_points(),
                        sessionId, allyRoll, enemyRoll, characterAlly, characterEnemy);

                this.sessionRepository.save(session);

                return session.SessionToResponseDto();
            }
            return null;
        }
        return null;
    }
}
