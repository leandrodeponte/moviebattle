package com.ada.challenge.moviebattle.service;

import com.ada.challenge.moviebattle.config.domain.Movie;
import com.ada.challenge.moviebattle.service.exceptions.BusinessException;
import com.ada.challenge.moviebattle.service.port.MoviePort;
import org.springframework.stereotype.Service;

@Service
public class SearchMovieUseCase implements UserCase<String, Movie>{

    private MoviePort moviePort;

    public SearchMovieUseCase(MoviePort moviePort) {
        this.moviePort = moviePort;
    }

    @Override
    public Movie execute(String roundId) throws BusinessException {
               var round =  moviePort.findById(roundId);
        return round.orElseThrow(() -> new BusinessException("The Movie was not found"));
    }
}