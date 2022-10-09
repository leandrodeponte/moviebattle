package com.ada.challenge.moviebattle.service;

import com.ada.challenge.moviebattle.domain.Game;
import org.springframework.stereotype.Service;

@Service
public class EndGameUseCase implements UserCase<String, Game>{
    @Override
    public Game execute(String s) {
        return null;
    }
}
