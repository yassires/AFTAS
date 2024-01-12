package com.youcode.aftas.Services;

import com.youcode.aftas.DTO.RankingDto;
import com.youcode.aftas.entities.Ranking;
import com.youcode.aftas.entities.RankingKey;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RankingService {

    List<RankingDto> findAll();

    RankingDto registerMember(RankingDto rankingDto);

    List<Ranking> getRankingForCompetition(String competitionId);

    public void updateRanking(RankingKey rankingId, Ranking updatedRanking);

}
