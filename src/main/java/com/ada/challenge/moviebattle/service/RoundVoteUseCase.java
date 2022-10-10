package com.ada.challenge.moviebattle.service;

import com.ada.challenge.moviebattle.config.domain.Round;
import com.ada.challenge.moviebattle.config.domain.RoundStatus;
import com.ada.challenge.moviebattle.config.domain.VoteRequest;
import com.ada.challenge.moviebattle.service.exceptions.BusinessException;
import com.ada.challenge.moviebattle.service.port.RoundPort;
import org.springframework.stereotype.Service;

@Service
public class RoundVoteUseCase implements UserCase<VoteRequest, Round>{

    private final RoundPort roundPort;
    private final SearchRoundUseCase searchRoundUseCase;
    private final SearchMovieUseCase searchMovieUseCase;

    public RoundVoteUseCase( RoundPort roundPort, SearchRoundUseCase searchRoundUseCase,
                             SearchMovieUseCase searchMovieUseCase) {
        this.roundPort = roundPort;
        this.searchRoundUseCase = searchRoundUseCase;
        this.searchMovieUseCase = searchMovieUseCase;
    }

    @Override
    public Round execute(VoteRequest voteRequest) throws BusinessException {
        var round = searchRoundUseCase.execute(voteRequest.getRoundId());
        if(round.getMovies().stream()
                .anyMatch( m -> m.getImdbID().equalsIgnoreCase(voteRequest.getMovieId()))) {
            var movie = searchMovieUseCase.execute(voteRequest.getMovieId());
            round.setSelectedMovieImdbID(movie.getImdbID());
            round.setStatus(RoundStatus.SELECTED);
            return roundPort.update(round);
        }
        throw new BusinessException("The movie is not on the Round.");
    }
}
