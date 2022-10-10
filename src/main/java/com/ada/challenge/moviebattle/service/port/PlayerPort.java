package com.ada.challenge.moviebattle.service.port;

import com.ada.challenge.moviebattle.config.domain.Player;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlayerPort {
    Optional<Player> findById(UUID id);
    List<Player> findAll();
}
