package com.youcode.aftas.repository;

import com.youcode.aftas.entities.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface CompetitionRepository extends JpaRepository<Competition, String> {
    Boolean existsByDate(LocalDate date);
}
