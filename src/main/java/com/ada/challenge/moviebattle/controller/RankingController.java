package com.ada.challenge.moviebattle.controller;

import com.ada.challenge.moviebattle.config.domain.Ranking;
import com.ada.challenge.moviebattle.service.RankingUseCase;
import com.ada.challenge.moviebattle.service.exceptions.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ranking")
public class RankingController {

    private final RankingUseCase rankingUseCase;

    private static Integer NR_PLAYERS = 5;

    public RankingController(RankingUseCase rankingUseCase) {
        this.rankingUseCase = rankingUseCase;
    }

    @GetMapping
    public @ResponseBody ResponseEntity<Ranking> get() throws BusinessException {
        var ranking = rankingUseCase.execute(NR_PLAYERS);
        return ResponseEntity.ok(ranking);
    }

}
