package com.ada.challenge.moviebattle.domain;

import lombok.Data;

@Data
public class Vote {
    private Movie movie;
    private Player player;
}
