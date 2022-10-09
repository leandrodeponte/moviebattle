package com.ada.challenge.moviebattle.repository;

import com.ada.challenge.moviebattle.domain.Movie;
import com.ada.challenge.moviebattle.service.port.MoviePort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class MovieDBRepository implements MoviePort {

    private MovieJPAEntityRepository repository;

    public MovieDBRepository(MovieJPAEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Movie> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public List<Movie> findAll() {
        return repository.findAll();
    }
}
