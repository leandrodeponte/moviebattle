package com.ada.challenge.moviebattle.controller;

import com.ada.challenge.moviebattle.controller.dto.RoundDTO;
import com.ada.challenge.moviebattle.controller.dto.VoteDTO;
import com.ada.challenge.moviebattle.service.CreateNewRoundUseCase;
import com.ada.challenge.moviebattle.service.RoundVoteUseCase;
import com.ada.challenge.moviebattle.service.exceptions.BusinessException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RoundController {

    private final CreateNewRoundUseCase createNewRoundUseCase;
    private final RoundVoteUseCase roundVoteUseCase;


    public RoundController(CreateNewRoundUseCase createNewRoundUseCase, RoundVoteUseCase roundVoteUseCase) {
        this.createNewRoundUseCase = createNewRoundUseCase;
        this.roundVoteUseCase = roundVoteUseCase;
    }

    @Operation(summary = "Creates a new round for an existing game")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Round created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoundDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Game not found",
                    content = @Content)})
    @PostMapping("/game/{id}/round")
    public @ResponseBody ResponseEntity<RoundDTO> post(@PathVariable String id) throws BusinessException {
        var round =   RoundDTO.from(createNewRoundUseCase.execute(id));
        return ResponseEntity.status(HttpStatus.CREATED).body(round);

    }

    @Operation(summary = "Updates a round, selecting a movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Round voted",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Movie seelected is not available",
                    content = @Content)})
    @PatchMapping("/round/{id}/vote")
    public @ResponseBody ResponseEntity post(@PathVariable String id,
                                                       @RequestBody VoteDTO vote)
            throws BusinessException {
        var round = RoundDTO.from(roundVoteUseCase.execute(vote.to(id)));
        return ResponseEntity.noContent().build();
    }

}
