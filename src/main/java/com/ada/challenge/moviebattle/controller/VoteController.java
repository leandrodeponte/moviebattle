package com.ada.challenge.moviebattle.controller;

import com.ada.challenge.moviebattle.controller.dto.RoundDTO;
import com.ada.challenge.moviebattle.controller.dto.VoteDTO;
import com.ada.challenge.moviebattle.service.CreateNewRoundUseCase;
import com.ada.challenge.moviebattle.service.SearchRoundUseCase;
import com.ada.challenge.moviebattle.service.exceptions.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/round")
public class VoteController {

    private final SearchRoundUseCase searchRoundUseCase;

    public VoteController(SearchRoundUseCase searchRoundUseCase) {
        this.searchRoundUseCase = searchRoundUseCase;
    }

    @PostMapping("/{id}/vote")
    public @ResponseBody ResponseEntity<RoundDTO> post(@PathVariable String id, VoteDTO vote) throws BusinessException {
        var round = RoundDTO.from(searchRoundUseCase.execute(id));
        return ResponseEntity.status(HttpStatus.CREATED).body(round);
    }
}