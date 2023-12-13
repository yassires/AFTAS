package com.youcode.aftas.Services.Impl;

import com.youcode.aftas.DTO.add.AddMemberDto;
import com.youcode.aftas.DTO.get.MemberDto;
import com.youcode.aftas.Services.MemberService;
import com.youcode.aftas.entities.Member;
import com.youcode.aftas.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;
    @Override
    public Member getMemberByIdentityNumber(String identity_number) {
        return null;
    }

    @Override
    public MemberDto addMember(AddMemberDto addMemberDto) {

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
}
