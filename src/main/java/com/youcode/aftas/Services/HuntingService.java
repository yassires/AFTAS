package com.youcode.aftas.Services;

import com.youcode.aftas.DTO.HuntingDto;
import com.youcode.aftas.entities.Hunting;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HuntingService {

    List<HuntingDto> getAllHunting();
    HuntingDto addHuntingResult(HuntingDto huntingDto);
    List<HuntingDto> getHuntingByCompetition(String competitionCode);
    List<HuntingDto> getHuntingsByMember( Integer memberId);

    public List<HuntingDto> getAllHuntingOfMemberInCompetition(Integer memberId, String competitionId);

}
