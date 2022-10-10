package com.ada.challenge.moviebattle.infrasctructure.repository;

import com.ada.challenge.moviebattle.config.domain.Round;
import com.ada.challenge.moviebattle.service.port.RoundPort;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class RoundDBRepository implements RoundPort {

    private RoundJPAEntityRepository roundJPAEntityRepository;

    public RoundDBRepository(RoundJPAEntityRepository roundJPAEntityRepository) {
        this.roundJPAEntityRepository = roundJPAEntityRepository;
    }

    @Override
    public Round save(Round round) {
        round.setId(UUID.randomUUID());
        return roundJPAEntityRepository.save(round);
    }

    @Override
    public Round update(Round round) {
        return  roundJPAEntityRepository.save(round);
    }

    @Override
    public Optional<Round> findById(UUID id) {
        return roundJPAEntityRepository.findById(id);
    }
}
