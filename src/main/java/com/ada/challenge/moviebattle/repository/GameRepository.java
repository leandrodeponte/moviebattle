package com.ada.challenge.moviebattle.repository;

import com.ada.challenge.moviebattle.domain.Game;
import com.ada.challenge.moviebattle.repository.dto.GameEntity;
import com.ada.challenge.moviebattle.service.port.GamePort;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class GameRepository implements GamePort {

    private GameEntityRepository gameEntityRepository;

    public GameRepository(GameEntityRepository gameEntityRepository) {
        this.gameEntityRepository = gameEntityRepository;
    }

    @Override
    public Game save(Game game) {
        var gameEntity =  gameEntityRepository.save(GameEntity.from(game));
        return GameEntity.to(gameEntity);
    }

    @Override
    public Optional<Game> findById(Long id) {
         var game = gameEntityRepository.findById(id);
         return game.isEmpty() ? Optional.empty() : Optional.of(GameEntity.to(game.get()));
    }
}
