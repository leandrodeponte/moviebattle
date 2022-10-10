package com.ada.challenge.moviebattle.infrasctructure.repository;

import com.ada.challenge.moviebattle.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GameJPAEntityRepository extends JpaRepository<Game, UUID> {

    List<Game> findByPlayerId(UUID playerId);

}

