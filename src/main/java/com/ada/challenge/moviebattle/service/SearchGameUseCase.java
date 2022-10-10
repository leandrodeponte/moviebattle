package com.ada.challenge.moviebattle.service;

import com.ada.challenge.moviebattle.config.domain.Game;
import com.ada.challenge.moviebattle.service.exceptions.ResourceNotFoundException;
import com.ada.challenge.moviebattle.service.port.GamePort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SearchGameUseCase implements UserCase<String, Game>{

    private GamePort gamePort;

    public SearchGameUseCase(GamePort gamePort) {
        this.gamePort = gamePort;
    }

    @Override
    public Game execute(String gameId) throws ResourceNotFoundException {
        var id = UUID.fromString(gameId);
        return  gamePort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The game does not exist."));
    }
}
