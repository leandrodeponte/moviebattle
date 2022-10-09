package com.ada.challenge.moviebattle.controller.dto;

import com.ada.challenge.moviebattle.domain.Game;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameDTO {
    private String createdPlayerId;
    public static GameDTO from(Game game){
        return GameDTO.builder()
                .createdPlayerId(game.getCreatedPlayerId())
                .build();
    }
    public static Game to(GameDTO game){
        return Game.builder()
                .createdPlayerId(game.createdPlayerId)
                .build();
    }
}
