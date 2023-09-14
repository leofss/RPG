package com.avanade.rpg.service;

import com.avanade.rpg.dto.LogResponseDto;
import com.avanade.rpg.entity.Log;
import com.avanade.rpg.exceptions.EntityNotFoundException;
import com.avanade.rpg.repository.LogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {
    private final LogRepository logRepository;

    public LogService(LogRepository logRepository, SessionService sessionService) {
        this.logRepository = logRepository;
    }

    public List<LogResponseDto> getHistoryBySessionId(Long sessionId){
        List<Log> logs = logRepository.findBySession_Id(sessionId);
        if(logs.isEmpty()){
            throw new EntityNotFoundException("Session with id " + sessionId + " not found");
        }else{
            return logs.stream().map(Log::logToDto)
                    .toList();
        }
    }

}
