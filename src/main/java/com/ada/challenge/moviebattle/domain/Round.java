package com.ada.challenge.moviebattle.domain;

import lombok.Data;

import java.util.List;

@Data
public class Round {
    private String id;
    private List<Movie> movies;
    private List<Vote> votes;
}
