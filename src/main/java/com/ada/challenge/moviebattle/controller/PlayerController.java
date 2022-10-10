package com.ada.challenge.moviebattle.controller;

import com.ada.challenge.moviebattle.controller.dto.GameDTO;
import com.ada.challenge.moviebattle.domain.LoginRequest;
import com.ada.challenge.moviebattle.domain.Player;
import com.ada.challenge.moviebattle.service.PlayerLoginUseCase;
import com.ada.challenge.moviebattle.service.exceptions.BusinessException;
import com.ada.challenge.moviebattle.service.exceptions.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/player")
public class PlayerController {

    private PlayerLoginUseCase playerLoginUseCase;

    public PlayerController(PlayerLoginUseCase playerLoginUseCase) {
        this.playerLoginUseCase = playerLoginUseCase;
    }

    @Operation(summary = "Authenticates the user.")
    @PostMapping("/login")
    public @ResponseBody ResponseEntity<Player> login6(@RequestBody LoginRequest loginRequest )
            throws BusinessException {
        var player =  playerLoginUseCase.execute(loginRequest);
        return ResponseEntity.ok(player);
    }

}
