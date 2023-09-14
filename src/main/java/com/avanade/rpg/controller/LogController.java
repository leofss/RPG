package com.avanade.rpg.controller;

import com.avanade.rpg.dto.payloads.responses.LogResponseDto;
import com.avanade.rpg.service.LogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/history")
public class LogController {
    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping("/{sessionId}")
    public List<LogResponseDto> getHistoryBySessionId(@PathVariable Long sessionId){
        return logService.getHistoryBySessionId(sessionId);
    }
}
