package com.ada.challenge.moviebattle.service;

import com.ada.challenge.moviebattle.domain.Game;
import com.ada.challenge.moviebattle.domain.GameStatus;
import com.ada.challenge.moviebattle.service.exceptions.ResourceNotFoundException;
import com.ada.challenge.moviebattle.service.port.GamePort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FinishGameUseCase implements UserCase<String, Game>{

    private final GamePort gamePort;

    public FinishGameUseCase(GamePort gamePort) {
        this.gamePort = gamePort;
    }

    @Override
    public Game execute(String gameId) throws ResourceNotFoundException {
        var id = UUID.fromString(gameId);
        Game game =  gamePort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The game does not exist."));
        game.setStatus(GameStatus.FINISHED);
        return gamePort.update(game);
    }
}

