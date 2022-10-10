package com.ada.challenge.moviebattle.infrasctructure.repository;

import com.ada.challenge.moviebattle.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieJPAEntityRepository extends JpaRepository<Movie, String> {
}

