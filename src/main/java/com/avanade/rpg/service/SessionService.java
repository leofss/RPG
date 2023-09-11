package com.avanade.rpg.service;

import com.avanade.rpg.dto.*;
import com.avanade.rpg.entity.Character;
import com.avanade.rpg.entity.Session;
import com.avanade.rpg.repository.CharacterRepository;
import com.avanade.rpg.repository.SessionRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.cfg.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
@Slf4j
public class SessionService {
    private final SessionRepository sessionRepository;
    private final CharacterRepository characterRepository;
    private HttpServletRequest request;

    public SessionService(SessionRepository sessionRepository, CharacterRepository characterRepository,
                          HttpServletRequest request) {
        this.sessionRepository = sessionRepository;
        this.characterRepository = characterRepository;
        this.request = request;
    }

    private boolean checkIfCharacterExistsById(Long id){
        return characterRepository.existsById(id);
    }

    private boolean checkIfEnemyIsMonster(Long id){
        Optional<Character> character = characterRepository.findById(id);
        CharacterType type = character.get().getCharacterType();
        return type == CharacterType.MONSTER;
    }

    private Character retrieveCharacterById(Long id){
        Optional<Character> character = characterRepository.findById(id);
        return character.get();
    }

    private Session retrieveSessionById(Long id){
        Optional<Session> session = sessionRepository.findById(id);
        return session.get();
    }

    private Optional<Character> retrieveRandomMonster(){
        List<Character> characterList = characterRepository.findAll();
        return characterList.stream()
                .filter(character -> character.getCharacterType() == CharacterType.MONSTER)
                .findFirst();

    }

    private int getResultFromCalculate(String endpoint, Long id) throws Exception {
        String baseUrl = request.getRequestURL().toString();
        String requestURI = request.getRequestURI();

        baseUrl = baseUrl.replace(requestURI, "");

        String serverUrl = baseUrl + "/calculate/" + endpoint + "/" + id;

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(serverUrl))
                .GET()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        JsonNode jsonResponse = objectMapper.readTree(response.body());
        int result = jsonResponse.get("result").asInt();

        return result;
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

    private boolean isAlly(Long sessionId, Long id){
        return sessionRepository.isAlly(sessionId, id);
    }

    private void updateSessionTurn(Session session){
        Session sessionUpdated = new Session();
        int turnCount = sessionUpdated.getTurnCount();
        sessionUpdated.setTurnCount(turnCount + 1);
    }

    private int updateSessionHealthPoints(Long sessionId, SessionTeamEnum currentTurn, int damage){
        Session currentSession = retrieveSessionById(sessionId);
        int currentHp;
        if(currentTurn.equals(SessionTeamEnum.ALLY)){
            currentHp = currentSession.getCurrentEnemyHealthPoints();
            currentSession.setCurrentEnemyHealthPoints(currentHp - damage);
            sessionRepository.save(currentSession);
            return currentSession.getCurrentEnemyHealthPoints();
        }else{
            currentHp = currentSession.getCurrentAllyHealthPoints();
            currentSession.setCurrentAllyHealthPoints(currentHp - damage);
            sessionRepository.save(currentSession);
            return currentSession.getCurrentAllyHealthPoints();
        }
    }

    private void endSession(Long sessionId){
        Session currentSession = retrieveSessionById(sessionId);
        currentSession.setSessionOver(true);
        sessionRepository.save(currentSession);

    }

    private void updateSessionTurn(SessionTeamEnum currentEnum, Long sessionId){
        Session currentSession = retrieveSessionById(sessionId);
        if(currentEnum.equals(SessionTeamEnum.ALLY)){
            currentSession.setCurrentTurn(SessionTeamEnum.ENEMY);
        }else{
            currentSession.setCurrentTurn(SessionTeamEnum.ALLY);
        }
    }

    private boolean isAttackerTurn(Session session, SessionTeamEnum currentAttackerTeam) {
        return !session.isSessionOver() && session.getCurrentTurn().equals(currentAttackerTeam);
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

                int allyRoll;
                int enemyRoll;
                do {
                    allyRoll = rollDice(1, 20);
                    enemyRoll = rollDice(1, 20);
                } while (allyRoll == enemyRoll);

                SessionTeamEnum currentTurn = (allyRoll > enemyRoll) ? SessionTeamEnum.ALLY : SessionTeamEnum.ENEMY;

                Session session = new Session(characterAlly.getHealthPoints(), characterEnemy.getHealthPoints(),
                        allyRoll, enemyRoll, currentTurn, characterAlly, characterEnemy, 0, false);

                this.sessionRepository.save(session);

                return session.SessionToResponseDto();
            }
            return null;
        }
        return null;
    }

    public TurnResponseDto playTurn(TurnRequestDto turnRequestDto) throws Exception {
        Session session = retrieveSessionById(turnRequestDto.session_id());
        if(session.isSessionOver()){
            return new TurnResponseDto(0, 10, session.isSessionOver(),
                    "Sessão encerrada!");
        }
        Long attackerId = turnRequestDto.attacker_id();
        Long defenderId = turnRequestDto.defender_id();

        if (defenderId == attackerId) {
            return new TurnResponseDto(0, 10, session.isSessionOver(),
                    "Não é possível atacar a si mesmo");
        }

        SessionTeamEnum currentAttackerTeam = isAlly(session.getId(), attackerId) ? SessionTeamEnum.ALLY
                : SessionTeamEnum.ENEMY;

        if (isAttackerTurn(session, currentAttackerTeam)) {
            int defenseResult = getResultFromCalculate("defense", defenderId);
            int attackResult = getResultFromCalculate("attack", attackerId);

            int damage;
            int updatedHp;

            if (attackResult > defenseResult) {
                damage = getResultFromCalculate("damage", attackerId);
                updatedHp = updateSessionHealthPoints(session.getId(), currentAttackerTeam, damage);

                if (updatedHp <= 0) {
                    updateSessionTurn(session);
                    endSession(session.getId());
                    return new TurnResponseDto(damage, updatedHp, true, "Defensor foi abatido!");
                }

                updateSessionTurn(session);
                return new TurnResponseDto(damage, updatedHp, session.isSessionOver(), "Ataque com Sucesso");
            } else {
                updateSessionTurn(session);
                return new TurnResponseDto(0, 10, session.isSessionOver(),
                        "Ataque falhou");
            }
        } else {
            return new TurnResponseDto(0, 10, session.isSessionOver(),
                    "Não é seu turno");
        }
    }
}
