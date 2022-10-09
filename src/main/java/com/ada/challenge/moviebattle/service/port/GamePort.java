package com.ada.challenge.moviebattle.service.port;

import com.ada.challenge.moviebattle.domain.Game;
import com.ada.challenge.moviebattle.repository.dto.GameEntity;

import java.util.Optional;

public interface GamePort {
    Game save(Game game);
    Optional<Game> findById(Long id);
}
