package com.youcode.aftas.repository;

import com.youcode.aftas.entities.Competition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, String> {
    Competition findByDate(LocalDate date);
    Competition findByCode(String code);

    Page<Competition> findAll(Pageable pageable);

}
