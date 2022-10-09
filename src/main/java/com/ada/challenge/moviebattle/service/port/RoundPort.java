package com.ada.challenge.moviebattle.service.port;

import com.ada.challenge.moviebattle.domain.Round;

import java.util.Optional;
import java.util.UUID;

public interface RoundPort {
    Round save(Round round);
    Round update(Round round);
    Optional<Round> findById(UUID id);
}