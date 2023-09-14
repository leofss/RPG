package com.avanade.rpg.controller;

import com.avanade.rpg.dto.payloads.responses.CalculateResponseDto;
import com.avanade.rpg.service.CalculateService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calculate")
public class CalculateController {
    private final CalculateService calculateService;

    public CalculateController(CalculateService calculateService) {
        this.calculateService = calculateService;
    }

    @GetMapping("/defense/{defenderId}")
    public CalculateResponseDto calculateDefense(@PathVariable Long defenderId){
        return calculateService.calculateDefense(defenderId);
    }

    @GetMapping("/attack/{attackerId}")
    public CalculateResponseDto calculateAttack(@PathVariable Long attackerId){
        return calculateService.calculateAttack(attackerId);
    }

    @GetMapping("/damage/{attackerId}")
    public CalculateResponseDto calculateDamage(@PathVariable Long attackerId){
        return calculateService.calculateDamage(attackerId);
    }



}
