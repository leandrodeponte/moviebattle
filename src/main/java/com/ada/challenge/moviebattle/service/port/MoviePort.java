package com.ada.challenge.moviebattle.service.port;

import com.ada.challenge.moviebattle.config.domain.Movie;

import java.util.List;
import java.util.Optional;

public interface MoviePort {
    Optional<Movie> findById(String id);
    Movie save(Movie movie);
    List<Movie> findAll();
}
