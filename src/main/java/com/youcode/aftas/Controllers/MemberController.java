package com.youcode.aftas.Controllers;

import com.youcode.aftas.DTO.MemberDto;
import com.youcode.aftas.Services.MemberService;
import com.youcode.aftas.entities.Member;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public MemberDto save(@Valid @RequestBody MemberDto addMemberDto){
        return memberService.addMember(addMemberDto);
    }

    @GetMapping
    @ResponseBody
    public List<MemberDto> getAllMembers(){
        return memberService.getAllMembers();
    }



    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable("id") Integer num){
        memberService.deleteMember(num);
    }

    @GetMapping("/byCompetition/{competitionId}")
    public ResponseEntity<List<Member>> getMembersByCompetitionId(@PathVariable String competitionId) {
        List<Member> members = memberService.getMembersByCompetitionId(competitionId);
        return ResponseEntity.ok(members);
    }

}
