package com.ada.challenge.moviebattle.service;

import com.ada.challenge.moviebattle.domain.Movie;
import com.ada.challenge.moviebattle.service.exceptions.BusinessException;
import com.ada.challenge.moviebattle.service.port.MoviePort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class SearchMovieUseCaseUnitTest {

    @InjectMocks
    private SearchMovieUseCase searchMovieUseCase;

    @Mock
    private MoviePort moviePort;

    @Test
    void givenMovieExists_WhenSearch_ThenSuccess() {
        var movieId = "movieId";
        var movie = Movie.builder()
                .imdbID(movieId)
                .build();
        Mockito.when(moviePort.findById(movieId)).thenReturn(Optional.of(movie));
        Assertions.assertDoesNotThrow(() -> {
            var movieResponse = searchMovieUseCase.execute(movieId);
            Assertions.assertEquals(movieId, movieResponse.getImdbID());
        });
    }

    @Test
    void givenMovieDoesNotExist_WhenSearch_ThenThrowException() {
        var movieId = "movieId";
        Mockito.when(moviePort.findById(movieId)).thenReturn(Optional.empty());
        var exception = Assertions.assertThrows(BusinessException.class, () -> {
            searchMovieUseCase.execute(movieId);
        });
        Assertions.assertEquals("The Movie was not found",exception.getMessage());
    }

}
