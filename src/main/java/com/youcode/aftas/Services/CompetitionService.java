package com.youcode.aftas.Services;

import com.youcode.aftas.entities.Competition;
import com.youcode.aftas.entities.Ranking;

import java.util.List;

public interface CompetitionService {

    Competition getCompetitionById(Long id);

    List<Competition> getAllCompetitions();

    Competition addCompetition(Competition competition);

    Ranking addMemberToCompetition(Ranking ranking);

}
