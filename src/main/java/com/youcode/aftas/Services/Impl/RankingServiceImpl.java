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
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
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
}
