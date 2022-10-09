package com.ada.challenge.moviebattle.service;

import com.ada.challenge.moviebattle.domain.Game;
import com.ada.challenge.moviebattle.service.port.GamePort;
import org.springframework.stereotype.Service;

@Service
public class CreateGameUseCase implements UserCase<String, Game>{

    private final GamePort gamePort;

    public CreateGameUseCase(GamePort gamePort) {
        this.gamePort = gamePort;
    }

    @Override
    public Game execute(String createdPlayerId) {
        return gamePort.save(
                Game.builder()
                        .createdPlayerId(createdPlayerId)
                        .build()
        );
    }
}
