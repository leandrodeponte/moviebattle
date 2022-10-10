package com.ada.challenge.moviebattle.controller;

import com.ada.challenge.moviebattle.controller.dto.GameDTO;
import com.ada.challenge.moviebattle.service.CreateGameUseCase;
import com.ada.challenge.moviebattle.service.FinishGameUseCase;
import com.ada.challenge.moviebattle.service.SearchGameUseCase;
import com.ada.challenge.moviebattle.service.exceptions.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/game")
public class GameController {

    private final CreateGameUseCase createGameUseCase;
    private final SearchGameUseCase searchGameUseCase;
    private final FinishGameUseCase finishGameUseCase;

    public GameController(CreateGameUseCase createGameUseCase, SearchGameUseCase searchGameUseCase, FinishGameUseCase finishGameUseCase) {
        this.createGameUseCase = createGameUseCase;
        this.searchGameUseCase = searchGameUseCase;
        this.finishGameUseCase = finishGameUseCase;
    }

    @Operation(summary = "Retrieves a game")
    @GetMapping(value = "/{id}")
    public @ResponseBody ResponseEntity<GameDTO> get(@PathVariable String id) throws ResourceNotFoundException {
        var game = searchGameUseCase.execute(id);
        return ResponseEntity.ok(GameDTO.from(game));
    }

    @Operation(summary = "Creates a new game")
    @PostMapping
    public @ResponseBody ResponseEntity<GameDTO> post(@RequestBody GameDTO gameDTO) throws ResourceNotFoundException {
        var game =  GameDTO.from(createGameUseCase.execute(gameDTO.getCreatedPlayerId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(game);
    }

    @Operation(summary = "Finishes an existing game")
    @PatchMapping("/{id}/end")
    public @ResponseBody ResponseEntity<GameDTO> patch(@PathVariable String id) throws ResourceNotFoundException {
        var game = GameDTO.from(finishGameUseCase.execute(id));
        return ResponseEntity.status(HttpStatus.CREATED).body(game);
    }
}
