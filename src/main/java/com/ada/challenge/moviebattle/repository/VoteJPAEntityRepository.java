package com.ada.challenge.moviebattle.repository;

import com.ada.challenge.moviebattle.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VoteJPAEntityRepository extends JpaRepository<Vote, UUID> {
}

