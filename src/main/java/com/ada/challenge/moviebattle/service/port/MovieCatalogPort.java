package com.ada.challenge.moviebattle.service.port;

import com.ada.challenge.moviebattle.config.domain.Movie;

import java.util.List;

public interface MovieCatalogPort {
    List<Movie> retrieveMovies(Long nrOfMovies);
}
