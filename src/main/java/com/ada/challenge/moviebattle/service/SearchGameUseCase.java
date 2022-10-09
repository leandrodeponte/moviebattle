package com.ada.challenge.moviebattle.service;

import com.ada.challenge.moviebattle.domain.Game;
import com.ada.challenge.moviebattle.service.port.GamePort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SearchGameUseCase implements UserCase<String, Game>{

    private GamePort gamePort;

    public SearchGameUseCase(GamePort gamePort) {
        this.gamePort = gamePort;
    }

    @Override
    public Game execute(String gameId) {
        var id = UUID.fromString(gameId);
        var game =  gamePort.findById(id);
        return game.orElseThrow();
    }
}
