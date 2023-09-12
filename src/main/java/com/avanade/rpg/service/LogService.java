package com.avanade.rpg.service;

import com.avanade.rpg.dto.LogResponseDto;
import com.avanade.rpg.entity.Log;
import com.avanade.rpg.repository.LogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {
    private final LogRepository logRepository;

    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public List<LogResponseDto> getHistoryBySessionId(Long sessionId){
        List<Log> logs = logRepository.findBySession_Id(sessionId);

        return logs.stream().map(Log::logToDto)
                .toList();
    }

}
