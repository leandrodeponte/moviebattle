package com.ada.challenge.moviebattle.controller.dto;

import com.ada.challenge.moviebattle.domain.Game;
import com.ada.challenge.moviebattle.domain.GameStatus;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class GameDTO {

    private UUID id;
    private String createdPlayerId;
    private GameStatus status;

    public static GameDTO from(Game game){
        return GameDTO.builder()
                .id(game.getId())
                .createdPlayerId(game.getCreatedPlayerId())
                .status(game.getStatus())
                .build();
    }
}
