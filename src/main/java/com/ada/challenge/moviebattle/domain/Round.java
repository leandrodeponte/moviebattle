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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
    @OneToMany
    private List<Movie> movies;
    @OneToOne
    private Movie selectedMovie;
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
        if(selectedMovie.equals(bestMovie))
            return 1;
        return 0;
    }

}
