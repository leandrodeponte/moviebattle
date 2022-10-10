package com.ada.challenge.moviebattle.service;

import com.ada.challenge.moviebattle.config.domain.Round;
import com.ada.challenge.moviebattle.config.domain.RoundStatus;
import com.ada.challenge.moviebattle.service.exceptions.BusinessException;
import com.ada.challenge.moviebattle.service.exceptions.ResourceNotFoundException;
import com.ada.challenge.moviebattle.service.port.GamePort;
import com.ada.challenge.moviebattle.service.port.RoundPort;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class CreateNewRoundUseCase implements UserCase<String, Round>{

    private final GamePort gamePort;
    private final RoundPort roundPort;
    private final SortMovieUseCase sortMovieUseCase;

    public CreateNewRoundUseCase(GamePort gamePort, RoundPort roundPort, SortMovieUseCase sortMovieUseCase) {
        this.gamePort = gamePort;
        this.roundPort = roundPort;
        this.sortMovieUseCase = sortMovieUseCase;
    }

    @Override
    public Round execute(String gameId) throws BusinessException {
        var id = UUID.fromString(gameId);
        var game = gamePort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The game does not exist."));
        if(game.hasPendingRounds())
            throw new BusinessException("New round only when current is finished.");
        var newRound = Round.builder()
                .game(game)
                .movies(sortMovieUseCase.execute(game))
                .status(RoundStatus.STARTED)
                .build();
        return roundPort.save(newRound);
    }

}
