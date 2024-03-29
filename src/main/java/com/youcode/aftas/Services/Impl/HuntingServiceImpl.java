package com.youcode.aftas.Services.Impl;

import com.youcode.aftas.DTO.CompetitionDto;
import com.youcode.aftas.DTO.FishDto;
import com.youcode.aftas.DTO.HuntingDto;
import com.youcode.aftas.Services.*;
import com.youcode.aftas.entities.*;
import com.youcode.aftas.repository.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class HuntingServiceImpl implements HuntingService {

    private final HuntingRepository huntingRepository;
    private final FishRepository fishRepository;
    private final CompetitionRepository competitionRepository;
    private final RankingRepository rankingRepository;
    private final MemberRepository memberRepository;
    private final FishService fishService;
    private final MemberService memberService;
    private final CompetitionService competitionService;
    private final RankingService rankingService;
    private final ModelMapper mapper;

    @Override
    public List<HuntingDto> getAllHunting() {

        return huntingRepository.findAll()
                .stream()
                .map(hunting ->
                mapper.map(hunting, HuntingDto.class)).collect(Collectors.toList());
    }

    @Override
    public HuntingDto addHuntingResult(HuntingDto huntingDto) {
        Optional<Competition> competition = competitionRepository.findById(huntingDto.getCompetition().getCode());
        if (competition.isEmpty()) {
            throw new RuntimeException("there's no competition with this code");
        }

        Optional<Member> member = memberRepository.findById(huntingDto.getMember().getNum());
        if (member.isEmpty()) {
            throw new RuntimeException("there's no member with this number");
        }

        Fish fish = fishRepository.findByName(huntingDto.getFish().getName());
        if (fish == null) {
            throw new RuntimeException("there's no fish with this name");
        }


        if (fish.getAverageWeight() > huntingDto.getFish().getAverageWeight()) {
            throw new RuntimeException("weight to small, the average weight for " + fish.getName() + ", is " + fish.getAverageWeight());
        }

        Hunting hunting = mapper.map(huntingDto, Hunting.class);

        Optional<Ranking> optionalRanking = rankingRepository.findById(new RankingKey(hunting.getCompetition().getCode(), hunting.getMember().getNum()));
        if (optionalRanking.isEmpty()){
            throw new RuntimeException("you are not registered to this competition.");
        }

        if (!(competition.isPresent() && competition.get().getEndTime().isBefore(LocalTime.now())) && competition.get().getStartTime().isAfter(LocalTime.now())){
            throw new RuntimeException("you can store a hunt only when competition is ongoing.");
        }

        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime competitionEndTime = LocalDateTime.of(competition.get().getDate(), competition.get().getEndTime());

        if (currentDateTime.isAfter(competitionEndTime)) {
            throw new RuntimeException("The competition has ended. No more hunting results can be added.");
        }


        Optional<Hunting> existsHunting = huntingRepository.findByCompetitionAndMemberAndFish(hunting.getFish().getName(), hunting.getCompetition().getCode(), hunting.getMember().getNum());
        if (existsHunting.isPresent()) {
            Hunting hunt = existsHunting.get();
            hunt.setNumberOfFish(hunt.getNumberOfFish() + 1);
            Hunting merged = huntingRepository.save(hunt);
            return mapper.map(merged, HuntingDto.class);
        } else {

            hunting.setNumberOfFish(1);
            hunting.setFish(fish);
            hunting.setMember(member.get());
            hunting.setCompetition(competition.get());



           /* optionalRanking.get().setScore(calculateScoreOfMember(hunting.getMember().getNum(),hunting.getCompetition().getCode()));
            System.out.println(optionalRanking.get().getId());
            rankingService.updateRanking(new RankingKey(hunting.getCompetition().getCode(), hunting.getMember().getNum()),optionalRanking.get());*/

            Hunting saved = huntingRepository.save(hunting);
            return mapper.map(saved, HuntingDto.class);
        }
    }
    public int calculateScoreOfMember(Integer memberId, String competitionId){
        int score = 0;
        List<HuntingDto> allHunting = getAllHuntingOfMemberInCompetition(memberId,competitionId);

        for(HuntingDto huntingDto : allHunting){

            score += huntingDto.getNumberOfFish() * huntingDto.getFish().getLevel().getPoints();
        }

        return score;
    }

    public List<HuntingDto> getHuntingByCompetition(String competitionCode) {

       /* return huntingRepository.findByCompetitionCode(competitionCode).stream()
                .map(hunting -> {
                            return HuntingDto.builder()
                                    .numberOfFish(hunting.getNumberOfFish())
                                    .fish(hunting.getFish())
                                    .member(hunting.getMember())
                                    .competition(hunting.getCompetition())
                                    .build();
                        })
                .collect(Collectors.toList());*/
       return huntingRepository.findByCompetitionCode(competitionCode).stream()
                .map(hunting -> mapper.map(hunting, HuntingDto.class))
                .collect(Collectors.toList());
    }



    @Override
    public List<HuntingDto> getHuntingsByMember(Integer memberId) {
    return huntingRepository.findByMember_Num(memberId);
    }

    @Override
    public List<HuntingDto> getAllHuntingOfMemberInCompetition(Integer memberId, String competitionId){
        return huntingRepository.getAllHuntingOfSameCompetitionAndSameMember(memberId,competitionId);
    }


}
