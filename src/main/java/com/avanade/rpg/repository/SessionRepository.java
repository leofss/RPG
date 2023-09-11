package com.avanade.rpg.repository;

import com.avanade.rpg.dto.SessionTeamEnum;
import com.avanade.rpg.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SessionRepository extends JpaRepository<Session, String> {
    Session findSessionBySessionId(String session_id);
    @Query(nativeQuery = true,
            value = "SELECT CASE WHEN :id IN (s.allyId, s.enemyId) THEN 'ALLY' ELSE 'ENEMY' END AS team FROM Session s WHERE :id IN (s.allyId, s.enemyId)")
    SessionTeamEnum findTeamByCharacterId(@Param("id") Long id);
}

