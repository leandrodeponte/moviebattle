package com.ada.challenge.moviebattle.repository;


import com.ada.challenge.moviebattle.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlayerJPAEntityRepository extends JpaRepository<Player, UUID> {
}


