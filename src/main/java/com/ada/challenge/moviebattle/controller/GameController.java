package com.ada.challenge.moviebattle.controller;

import com.ada.challenge.moviebattle.controller.dto.GameDTO;
import com.ada.challenge.moviebattle.service.CreateGameUseCase;
import com.ada.challenge.moviebattle.service.SearchGameUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/game")
public class GameController {

    private final CreateGameUseCase createGameUseCase;
    private final SearchGameUseCase searchGameUseCase;

    public GameController(CreateGameUseCase createGameUseCase, SearchGameUseCase searchGameUseCase) {
        this.createGameUseCase = createGameUseCase;
        this.searchGameUseCase = searchGameUseCase;
    }

    @GetMapping(value = "/{gameId}")
    public @ResponseBody ResponseEntity<GameDTO> get(@PathVariable Long gameId) {
        var game = searchGameUseCase.execute(gameId);
        return ResponseEntity.ok(GameDTO.from(game));
    }

    @PostMapping
    public @ResponseBody GameDTO post(GameDTO gameDTO) {
        return GameDTO.from(createGameUseCase.execute(gameDTO.getCreatedPlayerId()));
    }
}
