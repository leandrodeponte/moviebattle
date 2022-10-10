package com.ada.challenge.moviebattle.config.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

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
    @ManyToMany
    private List<Round> rounds;

    public Float getGrade(){
        return imdbRating * imdbVotes;
    }
}
