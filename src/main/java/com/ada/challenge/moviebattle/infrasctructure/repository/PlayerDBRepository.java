package com.ada.challenge.moviebattle.infrasctructure.repository;

import com.ada.challenge.moviebattle.domain.Player;
import com.ada.challenge.moviebattle.service.port.PlayerPort;
import org.springframework.stereotype.Repository;

import java.util.List;
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

    @Override
    public Optional<Player> findByUsernameAndPassword(String username, String password) {
        return repository.findByUsernameAndPassword(username, password);
    }

    @Override
    public List<Player> findAll() {
        return repository.findAll();
    }
}