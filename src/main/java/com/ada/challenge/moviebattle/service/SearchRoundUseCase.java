package com.ada.challenge.moviebattle.service;

import com.ada.challenge.moviebattle.domain.Round;
import com.ada.challenge.moviebattle.service.exceptions.BusinessException;
import com.ada.challenge.moviebattle.service.port.RoundPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SearchRoundUseCase implements UserCase<String, Round>{

    private RoundPort roundPort;

    public SearchRoundUseCase(RoundPort roundPort) {
        this.roundPort = roundPort;
    }

    @Override
    public Round execute(String roundId) throws BusinessException {
        var id = UUID.fromString(roundId);
        var round =  roundPort.findById(id);
        return round.orElseThrow(() -> new BusinessException("The Round was not found"));
    }
}