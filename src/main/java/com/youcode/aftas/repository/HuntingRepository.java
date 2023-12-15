package com.youcode.aftas.repository;

import com.youcode.aftas.entities.Competition;
import com.youcode.aftas.entities.Fish;
import com.youcode.aftas.entities.Hunting;
import com.youcode.aftas.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HuntingRepository extends JpaRepository<Hunting, Integer> {
    Optional<Hunting> findByFishAndCompetitionAndMember(Fish fish, Competition competition, Member member);
    List<Hunting> findByCompetitionAndMember(Competition competition, Member member);
}
