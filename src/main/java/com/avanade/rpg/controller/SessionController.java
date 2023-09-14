package com.avanade.rpg.controller;

import com.avanade.rpg.dto.payloads.requests.SessionRequestDto;
import com.avanade.rpg.dto.payloads.responses.SessionResponseDto;
import com.avanade.rpg.dto.payloads.requests.TurnRequestDto;
import com.avanade.rpg.dto.payloads.responses.TurnResponseDto;
import com.avanade.rpg.service.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/session")
public class SessionController {
    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SessionResponseDto createSession(@RequestBody SessionRequestDto body){
        return sessionService.createSession(body);
    }

    @PostMapping("/turn")
    public TurnResponseDto playTurn(@RequestBody TurnRequestDto body) throws Exception {
        return sessionService.playTurn(body);
    }



}
