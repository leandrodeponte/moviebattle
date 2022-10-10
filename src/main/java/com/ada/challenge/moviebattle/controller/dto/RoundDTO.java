package com.ada.challenge.moviebattle.controller.dto;

import com.ada.challenge.moviebattle.domain.Movie;
import com.ada.challenge.moviebattle.domain.Round;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Builder
@Data
public class RoundDTO {

    private UUID id;
    private List<Movie> movies;
    private String selectedMovie;

    public static RoundDTO from(Round round){
        return RoundDTO.builder()
                .id(round.getId())
                .movies(round.getMovies())
                .selectedMovie(round.getSelectedMovie() != null ? round.getSelectedMovie().getTitle() : null)
                .build();
    }

}
