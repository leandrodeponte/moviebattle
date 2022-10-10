package com.ada.challenge.moviebattle.infrasctructure.restclient.dto;

import com.ada.challenge.moviebattle.config.domain.Movie;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

@Data
public class MovieResponse {
    private String imdbID;
    @JsonProperty("Title")
    private String title;
    private String imdbRating;
    private String imdbVotes;

    public Movie parse( ) throws ParseException {
        NumberFormat format = NumberFormat.getInstance(Locale.US);
        return Movie.builder()
                .imdbID(this.imdbID)
                .title(this.title)
                .imdbRating(format.parse(this.imdbRating).floatValue())
                .imdbVotes(format.parse(this.imdbVotes).floatValue())
                .build();
    }

    public Boolean isValid(){
        return this.imdbRating != null
                && isNumeric(this.imdbRating)
                && this.imdbVotes!= null
                && isNumeric(this.imdbVotes);
    }

    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}
