package com.ada.challenge.moviebattle.service;

import com.ada.challenge.moviebattle.domain.Vote;
import com.ada.challenge.moviebattle.domain.VoteRequest;
import com.ada.challenge.moviebattle.service.port.RoundPort;
import com.ada.challenge.moviebattle.service.port.VotePort;
import org.springframework.stereotype.Service;

@Service
public class CreateVoteUseCase implements UserCase<VoteRequest, Vote>{

    private final VotePort votePort;
    private final RoundPort roundPort;
    private final SearchRoundUseCase searchRoundUseCase;
    private final SearchMovieUseCase searchMovieUseCase;
    private final SearchPlayerUseCase searchPlayerUseCase;

    public CreateVoteUseCase(VotePort votePort, RoundPort roundPort, SearchRoundUseCase searchRoundUseCase, SearchMovieUseCase searchMovieUseCase, SearchPlayerUseCase searchPlayerUseCase) {
        this.votePort = votePort;
        this.roundPort = roundPort;
        this.searchRoundUseCase = searchRoundUseCase;
        this.searchMovieUseCase = searchMovieUseCase;
        this.searchPlayerUseCase = searchPlayerUseCase;
    }

    @Override
    public Vote execute(VoteRequest voteRequest) {
        var round = searchRoundUseCase.execute(voteRequest.getRoundId());
        var movie = searchMovieUseCase.execute(voteRequest.getMovie());
        var player = searchPlayerUseCase.execute(voteRequest.getPlayer());
        var vote = votePort.save(
                Vote.builder()
                .player(player)
                .movie(movie)
                .build());
        var votes = round.getVotes();
        votes.add(vote);
        round.setVotes(votes);
        roundPort.save(round);
        return vote;
    }
}
