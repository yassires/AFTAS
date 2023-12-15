package com.youcode.aftas.repository;

import com.youcode.aftas.DTO.MemberDto;
import com.youcode.aftas.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Integer> {


    @Query("select m from Member m where m.identityNumber = ?1")
    Member findByIdentityNumber(String identityNumber);
}
