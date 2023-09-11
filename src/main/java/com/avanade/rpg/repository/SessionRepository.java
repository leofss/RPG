package com.avanade.rpg.repository;

import com.avanade.rpg.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SessionRepository extends JpaRepository<Session, Long> {
    @Query(nativeQuery = true, value = "SELECT CASE WHEN s.ally_id = :attackerId THEN true ELSE false END " +
            "FROM sessions s WHERE s.id = :sessionId")
    boolean isAlly(@Param("sessionId") Long sessionId, @Param("attackerId") Long attackerId);

    @Query(nativeQuery = true, value = "SELECT COUNT(s) > 0 FROM sessions s " +
            "WHERE s.id = :sessionId " +
            "AND (:characterId IN (s.ally_id) OR :characterId IN (s.enemy_id))")
    boolean isCharacterInSession(@Param("sessionId") Long sessionId, @Param("characterId") Long characterId);



}

