package com.ada.challenge.moviebattle.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Game {
    private int id;
    private List<Movie> movies;
    private List<Player> players;
    private String createdPlayerId;
}
