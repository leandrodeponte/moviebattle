package com.ada.challenge.moviebattle.repository;

import com.ada.challenge.moviebattle.domain.Player;
import com.ada.challenge.moviebattle.service.port.PlayerPort;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class PlayerDBRepository implements PlayerPort {

    private PlayerJPAEntityRepository repository;

    public PlayerDBRepository(PlayerJPAEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Player> findById(UUID id) {
        return repository.findById(id);
    }
}