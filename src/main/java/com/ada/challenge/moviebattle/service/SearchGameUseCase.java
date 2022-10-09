package com.ada.challenge.moviebattle.service;

import com.ada.challenge.moviebattle.domain.Game;
import com.ada.challenge.moviebattle.service.port.GamePort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SearchGameUseCase implements UserCase<Long, Game>{

    private GamePort gamePort;

    public SearchGameUseCase(GamePort gamePort) {
        this.gamePort = gamePort;
    }

    @Override
    public Game execute(Long gameId) {
        Optional<Game> game =  gamePort.findById(gameId);
        return game.orElseThrow();
    }
}
