package com.ada.challenge.moviebattle.infrasctructure.repository;

import com.ada.challenge.moviebattle.config.domain.Movie;
import com.ada.challenge.moviebattle.service.port.MoviePort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MovieDBRepository implements MoviePort {

    private MovieJPAEntityRepository repository;

    public MovieDBRepository(MovieJPAEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Movie> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Movie save(Movie movie) { return repository.save(movie); }

    @Override
    public List<Movie> findAll() {
        return repository.findAll();
    }

}
