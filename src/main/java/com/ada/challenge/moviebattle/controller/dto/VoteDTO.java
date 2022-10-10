package com.ada.challenge.moviebattle.controller.dto;

import com.ada.challenge.moviebattle.domain.VoteRequest;
import lombok.Data;

@Data
public class VoteDTO {
    private String movieId;

    public VoteRequest to( String roundId ) {
        return VoteRequest.builder()
                .movieId(this.movieId)
                .roundId(roundId)
                .build();
    }
}
