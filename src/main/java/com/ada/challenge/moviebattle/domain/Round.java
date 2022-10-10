package com.ada.challenge.moviebattle.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Round {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;
    @ManyToMany
    private List<Movie> movies;
    private String selectedMovieImdbID;
    @Enumerated(EnumType.STRING)
    private RoundStatus status;
    @ManyToOne
    private Game game;

    public Boolean isFinished(){
        return RoundStatus.SELECTED.equals(this.status);
    }

    public Integer getPoints() {
        var bestMovie =  this.movies.stream()
                .max(Comparator.comparing(Movie::getGrade));
        if(bestMovie.isPresent()
                && bestMovie.get().getImdbID().equals(this.selectedMovieImdbID))
            return 1;
        return 0;
    }

}
