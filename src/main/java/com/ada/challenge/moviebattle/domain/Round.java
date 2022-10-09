package com.ada.challenge.moviebattle.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Round {
    @Id
    private UUID id;
    @OneToMany
    private List<Movie> movies;
    @OneToMany
    private List<Vote> votes;
    @Enumerated(EnumType.STRING)
    private GameStatus status;

    public Boolean isFinished(){
        return RoundStatus.FINISHED.equals(this.status);
    }

}
