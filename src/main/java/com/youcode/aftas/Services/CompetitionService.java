package com.youcode.aftas.Services;

import com.youcode.aftas.DTO.CompetitionDto;
import com.youcode.aftas.DTO.RankingDto;
import com.youcode.aftas.entities.Competition;
import com.youcode.aftas.entities.Ranking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompetitionService {

    CompetitionDto getCompetitionByCode(String code);

    List<CompetitionDto> getAllCompetitions();

    CompetitionDto addCompetition(CompetitionDto competitionDto );

    List<RankingDto> calculateScore(String code);

    Page<CompetitionDto> getAllCompetitions(Pageable pageable);


}
