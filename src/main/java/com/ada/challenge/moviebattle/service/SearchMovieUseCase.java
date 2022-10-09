package com.ada.challenge.moviebattle.service;

import com.ada.challenge.moviebattle.domain.Movie;
import com.ada.challenge.moviebattle.service.port.MoviePort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SearchMovieUseCase implements UserCase<String, Movie>{

    private MoviePort moviePort;

    public SearchMovieUseCase(MoviePort moviePort) {
        this.moviePort = moviePort;
    }

    @Override
    public Movie execute(String roundId) {
        var id = UUID.fromString(roundId);
        var round =  moviePort.findById(id);
        return round.orElseThrow();
    }
}