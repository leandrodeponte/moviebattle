package com.ada.challenge.moviebattle.service;

import com.ada.challenge.moviebattle.domain.Round;
import com.ada.challenge.moviebattle.service.exceptions.BusinessException;
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
        var game = gamePort.findById(id).orElseThrow();
        var rounds = game.getRounds()
                .stream()
                .anyMatch(r -> !r.isFinished());
        if(rounds)
            throw new BusinessException("New round only when current is finished.");
        return roundPort.save( Round.builder()
                .movies(sortMovieUseCase.execute(game))
                .build());
    }

}
