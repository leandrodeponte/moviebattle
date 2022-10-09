package com.ada.challenge.moviebattle.repository.dto;

import com.ada.challenge.moviebattle.domain.Game;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GameEntity {
    @Id
    private int id;
    private String createdPlayerId;
    public static GameEntity from(Game game){
        return new GameEntity();
    }
    public static Game to(GameEntity game){
        return Game.builder()
                .createdPlayerId(game.createdPlayerId)
                .id(game.id)
                .build();
    }
}
