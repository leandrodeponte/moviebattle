package com.ada.challenge.moviebattle.service;

import com.ada.challenge.moviebattle.config.domain.Game;
import com.ada.challenge.moviebattle.config.domain.Movie;
import com.ada.challenge.moviebattle.service.exceptions.BusinessException;
import com.ada.challenge.moviebattle.service.port.MoviePort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SortMovieUseCase implements UserCase<Game, List<Movie>>{

    private MoviePort moviePort;

    private static Integer MOVIES_NUMBER = 2;

    public SortMovieUseCase(MoviePort moviePort) {
        this.moviePort = moviePort;
    }

    @Override
    public List<Movie> execute(Game game) throws BusinessException {
        var movies = removePreviousMovies(game, moviePort.findAll());
        if(movies.size() < 2)
            throw new BusinessException("The game must have more then 2 movies.");
        Collections.shuffle(movies);
        return movies.subList(0, MOVIES_NUMBER);
    }

    private List<Movie> removePreviousMovies(Game game, List<Movie> movies){
        game.getRounds().forEach(
                r -> movies.remove(r.getMovies())
        );
        return movies;
    }
}
