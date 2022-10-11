package com.ada.challenge.moviebattle.controller.dto;

import com.ada.challenge.moviebattle.domain.VoteRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoteDTO {

    private String movieId;

    public VoteRequest to( String roundId ) {
        return VoteRequest.builder()
                .movieId(this.movieId)
                .roundId(roundId)
                .build();
    }
}
