package com.ada.challenge.moviebattle.service;

import com.ada.challenge.moviebattle.config.domain.Movie;
import com.ada.challenge.moviebattle.service.port.MovieCatalogPort;
import com.ada.challenge.moviebattle.service.port.MoviePort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RetrieveMovieCatalogUseCase implements UserCase<Long, List<Movie>>{

    private final MovieCatalogPort movieCatalogPort;

    private final MoviePort moviePort;

    public RetrieveMovieCatalogUseCase(MovieCatalogPort movieCatalogPort, MoviePort moviePort) {
        this.movieCatalogPort = movieCatalogPort;
        this.moviePort = moviePort;
    }

    @Override
    public List<Movie> execute(Long nrOfMovies) {
        var movies = movieCatalogPort.retrieveMovies(nrOfMovies);
        return movies.stream()
                .map(m -> moviePort.save(m))
                .collect(Collectors.toList());
    }

}
