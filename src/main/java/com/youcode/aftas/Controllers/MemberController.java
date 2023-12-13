package com.youcode.aftas.Controllers;

import com.youcode.aftas.DTO.add.AddMemberDto;
import com.youcode.aftas.DTO.get.MemberDto;
import com.youcode.aftas.Services.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public MemberDto save(@Valid @RequestBody AddMemberDto addMemberDto){
        return memberService.addMember(addMemberDto);
    }

}
