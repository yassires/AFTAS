package com.youcode.aftas.Services;

import com.youcode.aftas.DTO.add.AddMemberDto;
import com.youcode.aftas.DTO.get.MemberDto;
import com.youcode.aftas.entities.Member;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface MemberService {

    Member getMemberByIdentityNumber(String identity_number);
    MemberDto addMember(AddMemberDto addMemberDto);
    List<Member> searchMember(String name);
}
