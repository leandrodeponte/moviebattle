package com.ada.challenge.moviebattle.repository;

import com.ada.challenge.moviebattle.domain.Game;
import com.ada.challenge.moviebattle.service.port.GamePort;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class GameDBRepository implements GamePort {

    private GameJPAEntityRepository gameJPAEntityRepository;

    public GameDBRepository(GameJPAEntityRepository gameJPAEntityRepository) {
        this.gameJPAEntityRepository = gameJPAEntityRepository;
    }

    @Override
    public Game save(Game game) {
        game.setId(UUID.randomUUID());
        return  gameJPAEntityRepository.save(game);
    }

    @Override
    public Game update(Game game) {
        return  gameJPAEntityRepository.save(game);
    }

    @Override
    public Optional<Game> findById(UUID id) {
         return gameJPAEntityRepository.findById(id);
    }
}
