package com.ada.challenge.moviebattle.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.UUID;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Vote {
    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;
    @OneToOne
    private Movie movie;
    @OneToOne
    private Player player;
}
