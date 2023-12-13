package com.youcode.aftas.Services;

import com.youcode.aftas.entities.Member;

import java.util.List;
public interface MemberService {

    Member getMemberByIdentityNumber(String identity_number);
    Member addMember(Member member);
    List<Member> searchMember(String name);
}
