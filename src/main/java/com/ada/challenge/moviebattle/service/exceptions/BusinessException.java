package com.ada.challenge.moviebattle.service.exceptions;

public class BusinessException extends Exception {
    public BusinessException(String errorMessage) {
        super(errorMessage);
    }
}
