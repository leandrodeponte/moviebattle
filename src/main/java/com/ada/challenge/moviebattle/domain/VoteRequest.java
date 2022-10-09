package com.ada.challenge.moviebattle.domain;

import lombok.Data;

@Data
public class VoteRequest {
    private String roundId;
    private String movie;
    private String player;
}
