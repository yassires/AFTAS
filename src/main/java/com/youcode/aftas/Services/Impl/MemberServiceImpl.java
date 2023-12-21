package com.youcode.aftas.Services.Impl;

import com.youcode.aftas.DTO.MemberDto;
import com.youcode.aftas.Services.MemberService;
import com.youcode.aftas.entities.Member;
import com.youcode.aftas.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<MemberDto> getAllMembers() {
        List<MemberDto> list = new ArrayList<>();

        memberRepository.findAll().forEach(member -> {
            list.add(modelMapper.map(member, MemberDto.class));
        });

        return list;
    }
    @Override
    public MemberDto addMember(MemberDto addMemberDto) {

        if (memberRepository.findById(addMemberDto.getNum()).isPresent()) {
            throw new RuntimeException("there's a member with this Num");
        }

        Member member = modelMapper.map(addMemberDto,Member.class);
        Member saved = memberRepository.save(member);
        return modelMapper.map(saved,MemberDto.class);
    }

    @Override
    public List<Member> searchMember(String name) {
        return null;
    }

    @Override
    public void deleteMember(Integer num) {
        memberRepository.deleteById(num);
    }

    public MemberDto searchMemberByIdentityNumber(String identityNumber) {
        Member member = memberRepository.findByIdentityNumber(identityNumber);
        return modelMapper.map(member, MemberDto.class);
    }

    @Override
    public List<Member> getMembersByCompetitionId(String competitionCode) {
        return memberRepository.findMembersByCompetitionCode(competitionCode);
    }
}
