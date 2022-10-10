package com.ada.challenge.moviebattle.service.exceptions;

public class ResourceNotFoundException extends BusinessException {
    public ResourceNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
