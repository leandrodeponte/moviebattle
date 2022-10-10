package com.ada.challenge.moviebattle.infrasctructure.repository;

import com.ada.challenge.moviebattle.config.domain.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoundJPAEntityRepository extends JpaRepository<Round, UUID> {
}
