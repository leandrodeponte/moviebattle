package com.ada.challenge.moviebattle.service.exceptions;

public class PlayerNotAuthorizedException extends BusinessException{
    public PlayerNotAuthorizedException(String errorMessage) {
        super(errorMessage);
    }
}
