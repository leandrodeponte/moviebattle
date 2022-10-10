package com.ada.challenge.moviebattle.controller;

import com.ada.challenge.moviebattle.controller.dto.GameDTO;
import com.ada.challenge.moviebattle.domain.Ranking;
import com.ada.challenge.moviebattle.service.RankingUseCase;
import com.ada.challenge.moviebattle.service.exceptions.BusinessException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Generates a ranking with N top players and their points")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ranking generated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Ranking.class)) })})
    @GetMapping
    public @ResponseBody ResponseEntity<Ranking> get() throws BusinessException {
        var ranking = rankingUseCase.execute(NR_PLAYERS);
        return ResponseEntity.ok(ranking);
    }

}
