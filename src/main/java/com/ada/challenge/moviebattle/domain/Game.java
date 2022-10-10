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
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;
    @ManyToOne
    private Player player;
    @OneToMany(mappedBy="game")
    private List<Round> rounds;
    @Enumerated(EnumType.STRING)
    private GameStatus status;

    public Boolean hasPendingRounds(){
        return this.rounds
                .stream()
                .anyMatch(r -> !r.isFinished());
    }

    public Integer getTotalPoints(){
        return this.rounds.stream()
                .map(Round::getPoints)
                .reduce(0, (a, b) -> a + b);
    }

}
