package com.ada.challenge.moviebattle.service;

import com.ada.challenge.moviebattle.domain.Game;
import com.ada.challenge.moviebattle.domain.Round;
import com.ada.challenge.moviebattle.domain.RoundStatus;
import com.ada.challenge.moviebattle.service.exceptions.BusinessException;
import com.ada.challenge.moviebattle.service.exceptions.ResourceNotFoundException;
import com.ada.challenge.moviebattle.service.port.GamePort;
import com.ada.challenge.moviebattle.service.port.RoundPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.ada.challenge.moviebattle.service.data.GameMockData.mockMovies;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class CreateNewRoundUseCaseUnitTest {

    @InjectMocks
    private CreateNewRoundUseCase createNewRoundUseCase;

    @Mock
    private GamePort gamePort;

    @Mock
    private RoundPort roundPort;

    @Mock
    private SortMovieUseCase sortMovieUseCase;

    @Test
    void givenGameExists_WhenCreateRound_ThenSuccess() throws BusinessException {
        var gameId = UUID.randomUUID();
        var game = Game.builder()
                .id(gameId)
                .build();
        Mockito.when(gamePort.findById(gameId)).thenReturn(Optional.of(game));
        Mockito.when(sortMovieUseCase.execute(game))
                .thenReturn(mockMovies());
        Mockito.when(roundPort.save(any()))
                .thenReturn(Round.builder().build());
        var round = createNewRoundUseCase.execute(gameId.toString());
        Assertions.assertNotNull(round);
    }

    @Test
    void givenGameExistsAndHasPendingRound_WhenCreateRound_ThenThrowBusiness(){
        var gameId = UUID.randomUUID();
        var game = Game.builder()
                .id(gameId)
                .rounds(List.of(Round.builder()
                                .status(RoundStatus.STARTED)
                                .build()))
                .build();
        Mockito.when(gamePort.findById(gameId)).thenReturn(Optional.of(game));
        var exception = Assertions.assertThrows(BusinessException.class,
                () -> createNewRoundUseCase.execute(gameId.toString()));
        Assertions.assertEquals("New round only when current is finished.", exception.getMessage());
    }

    @Test
    void givenGameDoesNotExist_WhenCreateRound_ThenThrowNotFound() {
        var gameId = UUID.randomUUID();
        Mockito.when(gamePort.findById(gameId)).thenReturn(Optional.empty());
        var exception = Assertions.assertThrows(ResourceNotFoundException.class,
                () -> createNewRoundUseCase.execute(gameId.toString()));
        Assertions.assertEquals("The game does not exist.", exception.getMessage());
    }

}
