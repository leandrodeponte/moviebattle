package com.ada.challenge.moviebattle.service;


import com.ada.challenge.moviebattle.service.exceptions.BusinessException;
import com.ada.challenge.moviebattle.service.port.GamePort;
import com.ada.challenge.moviebattle.service.port.PlayerPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.ada.challenge.moviebattle.service.data.GameMockData.mockGames;
import static com.ada.challenge.moviebattle.service.data.GameMockData.mockOneCorrectRound;
import static com.ada.challenge.moviebattle.service.data.GameMockData.mockPlayers;
import static com.ada.challenge.moviebattle.service.data.GameMockData.mockTwoCorrectRounds;


@ExtendWith(MockitoExtension.class)
public class RankingUseCaseUnitTest {

    @InjectMocks
    private RankingUseCase rankingUseCase;

    @Mock
    private PlayerPort playerPort;

    @Mock
    private GamePort gamePort;

    @Test
    void givenGetRanking_WhenUserVoted_ThenSuccessAndReturnOrderedList() throws BusinessException {
        var listOfPlayers = mockPlayers();
        var nrOfPlayersToShow = 3;

        Mockito.when(playerPort.findAll()).thenReturn(listOfPlayers);
        Mockito.when(gamePort.findByPlayerId(listOfPlayers.get(0).getId()))
                .thenReturn(mockGames(listOfPlayers.get(0), mockTwoCorrectRounds()));
        Mockito.when(gamePort.findByPlayerId(listOfPlayers.get(1).getId()))
                .thenReturn(mockGames(listOfPlayers.get(0), mockOneCorrectRound()));
        var ranking = rankingUseCase.execute(nrOfPlayersToShow);

        Assertions.assertEquals(nrOfPlayersToShow, ranking.getPoints().size());
        Assertions.assertEquals(2, ranking.getPoints().get(listOfPlayers.get(0).getUsername()));
        Assertions.assertEquals(
                listOfPlayers.get(0).getUsername(),
                ranking.getPoints().keySet().stream().findFirst().get());
        Assertions.assertEquals(1, ranking.getPoints().get(listOfPlayers.get(1).getUsername()));
        Assertions.assertEquals(
                listOfPlayers.get(0).getUsername(),
                ranking.getPoints().keySet().stream().findFirst().get());
    }

}