package com.youcode.aftas.repository;

import com.youcode.aftas.DTO.HuntingDto;
import com.youcode.aftas.entities.Competition;
import com.youcode.aftas.entities.Fish;
import com.youcode.aftas.entities.Hunting;
import com.youcode.aftas.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HuntingRepository extends JpaRepository<Hunting, Long> {
    Optional<Hunting> findByFishAndCompetitionAndMember(Fish fish, Competition competition, Member member);
    List<Hunting> findByCompetitionAndMember(Competition competition, Member member);

    List<HuntingDto> findByCompetition_Code(String competitionCode);
    List<HuntingDto> findByMember_Num(Integer memberId);}
