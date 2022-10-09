package com.ada.challenge.moviebattle.service.port;

import com.ada.challenge.moviebattle.domain.Vote;

public interface VotePort {
    Vote save(Vote vote);
}
