package com.ada.challenge.moviebattle.service;

import com.ada.challenge.moviebattle.domain.Game;
import com.ada.challenge.moviebattle.domain.Ranking;
import com.ada.challenge.moviebattle.service.exceptions.BusinessException;
import com.ada.challenge.moviebattle.service.port.GamePort;
import com.ada.challenge.moviebattle.service.port.PlayerPort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
        var map = new HashMap<String, Integer>();
        players.stream()
                .forEach( p -> {
                        var game = getBestGame(p.getId());
                        var points = game.isPresent() ? game.get().calculatePoints() : 0;
                        map.put(p.getUsername(), points );
                });
        var orderedMap = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(nrOfPlayers)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        return Ranking.builder()
                .points(orderedMap)
                .build();
    }

    private Optional<Game> getBestGame(UUID playerId){
        var games = gamePort.findByPlayerId(playerId);
        return games.stream()
                .max(Comparator.comparing(Game::getTotalPoints));
    }

}
