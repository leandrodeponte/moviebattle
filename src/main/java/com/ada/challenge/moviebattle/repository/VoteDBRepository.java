package com.ada.challenge.moviebattle.repository;

import com.ada.challenge.moviebattle.domain.Game;
import com.ada.challenge.moviebattle.domain.Vote;
import com.ada.challenge.moviebattle.service.port.GamePort;
import com.ada.challenge.moviebattle.service.port.VotePort;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class VoteDBRepository implements VotePort {

    private VoteJPAEntityRepository repository;

    public VoteDBRepository(VoteJPAEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Vote save(Vote vote) {
        vote.setId(UUID.randomUUID());
        return  repository.save(vote);
    }

}