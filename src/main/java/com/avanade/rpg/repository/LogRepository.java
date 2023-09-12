package com.avanade.rpg.repository;

import com.avanade.rpg.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface LogRepository extends JpaRepository<Log, Long> {
    List<Log> findBySession_Id(Long sessionId);
}
