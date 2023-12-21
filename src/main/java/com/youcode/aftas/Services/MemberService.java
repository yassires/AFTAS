package com.youcode.aftas.Services;

import com.youcode.aftas.DTO.MemberDto;
import com.youcode.aftas.entities.Member;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface MemberService {


    public List<MemberDto> getAllMembers();

    MemberDto addMember(MemberDto addMemberDto);
    List<Member> searchMember(String name);

    void deleteMember(Integer num);

    List<Member> getMembersByCompetitionId(String competitionCode);

}
