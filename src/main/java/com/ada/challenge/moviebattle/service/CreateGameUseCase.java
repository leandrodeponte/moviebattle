package com.ada.challenge.moviebattle.service;

import com.ada.challenge.moviebattle.domain.Game;
import com.ada.challenge.moviebattle.domain.GameStatus;
import com.ada.challenge.moviebattle.service.exceptions.ResourceNotFoundException;
import com.ada.challenge.moviebattle.service.port.GamePort;
import org.springframework.stereotype.Service;

@Service
public class CreateGameUseCase implements UserCase<String, Game>{

    private final GamePort gamePort;

    private final SearchPlayerUseCase searchPlayerUseCase;

    public CreateGameUseCase(GamePort gamePort, SearchPlayerUseCase searchPlayerUseCase) {
        this.gamePort = gamePort;
        this.searchPlayerUseCase = searchPlayerUseCase;
    }

    @Override
    public Game execute(String createdPlayerId) throws ResourceNotFoundException {
        var player = searchPlayerUseCase.execute(createdPlayerId);
        return gamePort.save(
                Game.builder()
                        .player(player)
                        .status(GameStatus.STARTED)
                        .build()
        );
    }
}
