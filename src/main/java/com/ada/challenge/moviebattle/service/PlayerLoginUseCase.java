package com.ada.challenge.moviebattle.service;

import com.ada.challenge.moviebattle.domain.LoginRequest;
import com.ada.challenge.moviebattle.domain.Player;
import com.ada.challenge.moviebattle.service.exceptions.BusinessException;
import com.ada.challenge.moviebattle.service.exceptions.PlayerNotAuthorizedException;
import com.ada.challenge.moviebattle.service.port.PlayerPort;
import org.springframework.stereotype.Service;

@Service
public class PlayerLoginUseCase implements UserCase<LoginRequest, Player>{

    private PlayerPort playerPort;

    public PlayerLoginUseCase(PlayerPort playerPort) {
        this.playerPort = playerPort;
    }

    @Override
    public Player execute(LoginRequest loginRequest) throws BusinessException {
        var player = playerPort.findByUsernameAndPassword(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        ).orElseThrow(() -> new PlayerNotAuthorizedException("The player does not exist or the password is incorrect."));
        return player;
    }
}
