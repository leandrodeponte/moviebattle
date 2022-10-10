package com.ada.challenge.moviebattle.service;

import com.ada.challenge.moviebattle.config.domain.Player;
import com.ada.challenge.moviebattle.service.exceptions.ResourceNotFoundException;
import com.ada.challenge.moviebattle.service.port.PlayerPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SearchPlayerUseCase implements UserCase<String, Player>{

    private PlayerPort playerPort;

    public SearchPlayerUseCase(PlayerPort playerPort) {
        this.playerPort = playerPort;
    }

    @Override
    public Player execute(String playerId) throws ResourceNotFoundException {
        var id = UUID.fromString(playerId);
        var player =  playerPort.findById(id);
        return player.orElseThrow(() -> new ResourceNotFoundException("The player does not exist."));
    }
}
