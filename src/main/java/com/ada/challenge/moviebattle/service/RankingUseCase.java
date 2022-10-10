package com.ada.challenge.moviebattle.service;

import com.ada.challenge.moviebattle.domain.Game;
import com.ada.challenge.moviebattle.domain.Ranking;
import com.ada.challenge.moviebattle.service.exceptions.BusinessException;
import com.ada.challenge.moviebattle.service.port.GamePort;
import com.ada.challenge.moviebattle.service.port.PlayerPort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@Service
public class RankingUseCase implements UserCase<Integer, Ranking>{

    private PlayerPort playerPort;

    private GamePort gamePort;

    public RankingUseCase(PlayerPort playerPort, GamePort gamePort) {
        this.playerPort = playerPort;
        this.gamePort = gamePort;
    }

    @Override
    public Ranking execute(Integer nrOfPlayers) throws BusinessException {
        var players = playerPort.findAll();
        HashMap map = new HashMap();
        players.stream()
                .forEach( p -> map.put(p.getId().toString(), getBestGame(p.getId())));
        return Ranking.builder()
                .points(map)
                .build();
    }

    private Optional<Game> getBestGame(UUID playerId){
        var games = gamePort.findByPlayerId(playerId);
        return games.stream()
                .max(Comparator.comparing(Game::getTotalPoints));
    }

}
