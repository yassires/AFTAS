package com.youcode.aftas.repository;

import com.youcode.aftas.DTO.MemberDto;
import com.youcode.aftas.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {


    @Query("select m from Member m where m.identityNumber = ?1")
    Member findByIdentityNumber(String identityNumber);


    @Query("SELECT m FROM Member m JOIN m.rankings r WHERE r.competition.code = :competitionCode")
    List<Member> findMembersByCompetitionCode(@Param("competitionCode") String competitionCode);
}
