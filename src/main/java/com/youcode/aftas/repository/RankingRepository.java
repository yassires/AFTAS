package com.youcode.aftas.repository;

import com.youcode.aftas.entities.Competition;
import com.youcode.aftas.entities.Ranking;
import com.youcode.aftas.entities.RankingKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RankingRepository extends JpaRepository<Ranking, RankingKey> {
    List<Ranking> findByCompetition(Competition competition);
}
