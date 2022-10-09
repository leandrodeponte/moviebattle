package com.ada.challenge.moviebattle.repository;

import com.ada.challenge.moviebattle.repository.dto.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameEntityRepository extends JpaRepository<GameEntity, Long> {

}

