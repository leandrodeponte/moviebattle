package com.ada.challenge.moviebattle.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;
    private String username;
    private String password;

}
