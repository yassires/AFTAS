package com.youcode.aftas.Services.Impl;

import com.youcode.aftas.DTO.RankingDto;
import com.youcode.aftas.Services.RankingService;
import com.youcode.aftas.entities.Competition;
import com.youcode.aftas.entities.Member;
import com.youcode.aftas.entities.Ranking;
import com.youcode.aftas.entities.RankingKey;
import com.youcode.aftas.repository.CompetitionRepository;
import com.youcode.aftas.repository.MemberRepository;
import com.youcode.aftas.repository.RankingRepository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RankingServiceImpl implements RankingService {

    private final RankingRepository rankingRepository;
    private final ModelMapper modelMapper;
    private final CompetitionRepository competitionRepository;
    private final MemberRepository memberRepository;
    @Override
    public List<RankingDto> findAll() {
        return rankingRepository.findAll().stream().map(ranking -> {
            return modelMapper.map(ranking, RankingDto.class);
        }).collect(Collectors.toList());

    }

    @Override
    public RankingDto registerMember(RankingDto rankingDto) {


        Optional<Competition> competition = competitionRepository.findById(rankingDto.getCompetition().getCode());
        if (competition.isEmpty()) {
            throw new RuntimeException("there's no competition with this code");
        }

        Optional<Member> member = memberRepository.findById(rankingDto.getMember().getNum());
        if (member.isEmpty()) {
            throw new RuntimeException("there's no member with this number");
        }

        Ranking ranking = modelMapper.map(rankingDto, Ranking.class);
            ranking.setId(new RankingKey(rankingDto.getCompetition().getCode(), rankingDto.getMember().getNum()));
        ranking.setRank(0);
        ranking.setScore(0);
        Optional<Ranking> ranking1 = rankingRepository.findById(ranking.getId());
        if (ranking1.isPresent()) {
            throw new RuntimeException("this member already registered in that competition");
        }

        if (!checkDate(competition.get())) {
            throw new RuntimeException("you can't register now, the register close before 24 hour.");
        }


        Ranking saved = rankingRepository.save(ranking);
        return modelMapper.map(saved, RankingDto.class);
    }

    private Boolean checkDate(Competition competition) {
        LocalDateTime competitionDateTime = LocalDateTime.of(competition.getDate(), competition.getStartTime());
        LocalDateTime now = LocalDateTime.now();

        return now.isBefore(competitionDateTime.minusHours(24));
    }

    public List<Ranking> getRankingForCompetition(String competitionId) {
        Competition competition = competitionRepository.findByCode(competitionId);
        LocalDateTime competitionEndDateTime = LocalDateTime.of(competition.getDate(), competition.getEndTime());
        LocalDateTime twoHoursAfterEnd = competitionEndDateTime.plusHours(2);

        if (LocalDateTime.now().isAfter(twoHoursAfterEnd)) {
            List<Ranking> rankings = rankingRepository.findByCompetitionOrderByScoreDesc(competition);
            boolean rankingsAlreadySet = rankings.stream().anyMatch(ranking -> ranking.getRank() != 0);

            if (rankingsAlreadySet) {
                return rankings.stream().limit(3).collect(Collectors.toList());
            } else {
                throw new ValidationException("Rankings have not already been set for this competition.");
            }

        } else {
            throw new ValidationException("Ranking can only be retrieved after endTime + 2 hours after it ends");
        }
    }


    @Override
    public void updateRanking(RankingKey rankingId, Ranking updatedRanking) {
        Ranking ranking = rankingRepository.findById(rankingId)
                .orElseThrow(() -> new NoSuchElementException("Ranking not found"));

        ranking.setScore(updatedRanking.getScore());
        rankingRepository.save(ranking);
        List<Ranking> rankings = rankingRepository.findAllByCompetitionCodeOrderByScoreDesc(ranking.getCompetition().getCode());

        for (int i = 0; i < rankings.size(); i++) {
            Ranking currentRanking = rankings.get(i);
            currentRanking.setRank(i + 1);
            rankingRepository.save(currentRanking);
        }
    }
}
