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
    @OneToMany
    private List<Player> players;
    @OneToMany
    private List<Round> rounds;
    private String createdPlayerId;
    @Enumerated(EnumType.STRING)
    private GameStatus status;

}
