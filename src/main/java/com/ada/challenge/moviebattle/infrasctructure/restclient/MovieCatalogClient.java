package com.ada.challenge.moviebattle.infrasctructure.restclient;

import com.ada.challenge.moviebattle.config.domain.Movie;
import com.ada.challenge.moviebattle.infrasctructure.restclient.dto.MovieResponse;
import com.ada.challenge.moviebattle.service.port.MovieCatalogPort;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@ConfigurationProperties(prefix = "application")
public class MovieCatalogClient implements MovieCatalogPort {

    private static String IMDB_PREFIX = "tt";
    private static String API_KEY = "91fa646e";

    @Override
    public List<Movie> retrieveMovies(Long nrOfMovies) {
        var imdbIDs = generateImdbIDs(nrOfMovies);
        return imdbIDs.stream()
                .map(id -> retrieveMovie(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private Optional<Movie> retrieveMovie(String imdbID) {
        RestTemplate restTemplate = new RestTemplate();
        String omdbURL
                = "https://omdbapi.com/?apikey="+API_KEY+"&i="+imdbID+"&plot=full";
        var movieResponse = restTemplate
                .getForEntity(omdbURL, MovieResponse.class)
                .getBody();
        Movie movie = null;
        try {
            if(movieResponse.isValid())
                movie = movieResponse.parse();
        }catch(ParseException e){
            return Optional.empty();
        }

        return Optional.ofNullable(movie);
    }

    private List<String> generateImdbIDs(Long nrOfMovies){
        return Arrays.stream(IntStream
                .generate(() -> new Random().nextInt(1000000) + 1000000)
                .limit(nrOfMovies).toArray())
                .mapToObj(n -> IMDB_PREFIX + n)
                .collect(Collectors.toList());
    }
}
