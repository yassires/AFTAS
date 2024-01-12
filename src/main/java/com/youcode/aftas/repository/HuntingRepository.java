package com.youcode.aftas.repository;

import com.youcode.aftas.DTO.HuntingDto;
import com.youcode.aftas.entities.Competition;
import com.youcode.aftas.entities.Fish;
import com.youcode.aftas.entities.Hunting;
import com.youcode.aftas.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HuntingRepository extends JpaRepository<Hunting, Long> {

    @Query("select h from Hunting h where h.fish.name = ?1 and h.competition.code = ?2 and h.member.num = ?3")
    Optional<Hunting> findByCompetitionAndMemberAndFish(String fish, String competition, Integer member);

    List<Hunting> findByCompetitionAndMember(Competition competition, Member member);

    /*@Query(value = "SELECT * FROM hunting  WHERE competition_code = :code", nativeQuery = true)
    List<HuntingDto> findByCompetitionCode(@Param("code") String competitionCode,Class<HuntingDto> type);*/

    @Query("SELECT h FROM Hunting h WHERE h.competition.code = ?1")
    List<Hunting> findByCompetitionCode(String competitionCode);



    List<HuntingDto> findByMember_Num(Integer memberId);

    @Query("SELECT h FROM Hunting h WHERE h.member.num = ?1 AND h.competition.code = ?2")
    List<HuntingDto> getAllHuntingOfSameCompetitionAndSameMember(Integer memberId, String competitionId);
}