package com.ada.challenge.moviebattle.service.port;

import com.ada.challenge.moviebattle.config.domain.Game;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GamePort {
    Game save(Game game);
    Game update(Game game);
    Optional<Game> findById(UUID id);
    List<Game>findByPlayerId(UUID playerId);
}
