package com.ada.challenge.moviebattle.service.port;

import com.ada.challenge.moviebattle.domain.Movie;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MoviePort {
    Optional<Movie> findById(UUID id);
    List<Movie> findAll();
}
