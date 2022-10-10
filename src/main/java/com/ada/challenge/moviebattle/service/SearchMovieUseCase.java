package com.ada.challenge.moviebattle.service;

import com.ada.challenge.moviebattle.domain.Movie;
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
    public Movie execute(String movieId) throws BusinessException {
       var movie =  moviePort.findById(movieId);
       return movie.orElseThrow(() -> new BusinessException("The Movie was not found"));
    }
}