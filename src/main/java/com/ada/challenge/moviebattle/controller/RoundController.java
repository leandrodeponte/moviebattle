package com.ada.challenge.moviebattle.controller;

import com.ada.challenge.moviebattle.controller.dto.RoundDTO;
import com.ada.challenge.moviebattle.service.CreateNewRoundUseCase;
import com.ada.challenge.moviebattle.service.exceptions.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/game")
public class RoundController {

    private final CreateNewRoundUseCase createNewRoundUseCase;

    public RoundController(CreateNewRoundUseCase createNewRoundUseCase) {
        this.createNewRoundUseCase = createNewRoundUseCase;
    }

    @PostMapping("/{id}/round")
    public @ResponseBody ResponseEntity<RoundDTO> post(@PathVariable String id) throws BusinessException {
         var round =   RoundDTO.from(createNewRoundUseCase.execute(id));
        return ResponseEntity.status(HttpStatus.CREATED).body(round);

    }

}
