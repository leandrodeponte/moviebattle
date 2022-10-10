package com.ada.challenge.moviebattle.domain;

import lombok.Builder;
import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
