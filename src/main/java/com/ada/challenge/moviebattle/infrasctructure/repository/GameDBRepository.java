package com.ada.challenge.moviebattle.infrasctructure.repository;

import com.ada.challenge.moviebattle.domain.Game;
import com.ada.challenge.moviebattle.service.port.GamePort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class GameDBRepository implements GamePort {

    private GameJPAEntityRepository repository;

    public GameDBRepository(GameJPAEntityRepository gameJPAEntityRepository) {
        this.repository = gameJPAEntityRepository;
    }

    @Override
    public Game save(Game game) {
        game.setId(UUID.randomUUID());
        return  repository.saveAndFlush(game);
    }

    @Override
    public Game update(Game game) {
        return repository.saveAndFlush(game);
    }

    @Override
    public Optional<Game> findById(UUID id) {
         return repository.findById(id);
    }

    @Override
    public List<Game> findByPlayerId(UUID playerId) {
        return repository.findByPlayerId(playerId);
    }

    @Override
    public List<Game> findAll() {
        return repository.findAll();
    }
}
