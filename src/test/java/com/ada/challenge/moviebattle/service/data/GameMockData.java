package com.ada.challenge.moviebattle.service.data;

import com.ada.challenge.moviebattle.domain.Game;
import com.ada.challenge.moviebattle.domain.GameStatus;
import com.ada.challenge.moviebattle.domain.Movie;
import com.ada.challenge.moviebattle.domain.Player;
import com.ada.challenge.moviebattle.domain.Round;

import java.util.List;
import java.util.UUID;

public class GameMockData {

    public static List<Player> mockPlayers(){
        return List.of(
                Player.builder()
                        .id(UUID.randomUUID())
                        .username("Player1")
                        .build(),
                Player.builder()
                        .id(UUID.randomUUID())
                        .username("Player2")
                        .build(),
                Player.builder()
                        .id(UUID.randomUUID())
                        .username("Player3")
                        .build(),
                Player.builder()
                        .id(UUID.randomUUID())
                        .username("Player4")
                        .build(),
                Player.builder()
                        .id(UUID.randomUUID())
                        .username("Player5")
                        .build()
        );
    }

    public static List<Round> mockOneCorrectRound(){
        return List.of(
                Round.builder()
                        .id(UUID.randomUUID())
                        .movies(mockMovies())
                        .selectedMovieImdbID("tt1537282")
                        .build()
        );
    }

    public static List<Round> mockTwoCorrectRounds(){
        return List.of(
                Round.builder()
                        .id(UUID.randomUUID())
                        .movies(mockMovies())
                        .selectedMovieImdbID("tt1537282")
                        .build(),
                Round.builder()
                        .id(UUID.randomUUID())
                        .movies(mockMovies())
                        .selectedMovieImdbID("tt1537282")
                        .build()
        );
    }

    public static List<Movie> mockMovies(){
        var id = UUID.randomUUID();
        return List.of(
                Movie.builder()
                        .title("Ghostown")
                        .imdbID("tt1537282")
                        .imdbVotes(8F)
                        .imdbRating(6.8F)
                        .build(),
                Movie.builder()
                        .title("Grace Period")
                        .imdbID("'tt1698509'")
                        .imdbVotes(5F)
                        .imdbRating(8.1F)
                        .build()
        );
    }

    public static List<Game> mockGames(
            Player player, List<Round> listOfRounds){
        return List.of(
                Game.builder()
                        .player(player)
                        .rounds(listOfRounds)
                        .status(GameStatus.FINISHED)
                        .build()
        );
    }
}
