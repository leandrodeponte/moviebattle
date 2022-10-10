package com.ada.challenge.moviebattle.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie {
    @Id
    private String imdbID;
    private String title;
    private Float imdbRating;
    private Float imdbVotes;

    public Float getGrade(){
        return imdbRating * imdbVotes;
    }
}
