package com.youcode.aftas.Services;

import com.youcode.aftas.DTO.HuntingDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HuntingService {

    List<HuntingDto> getAllHunting();
    HuntingDto addHuntingResult(HuntingDto huntingDto);
    List<HuntingDto> getHuntingsByCompetition(Long competitionId);
    List<HuntingDto> getHuntingsByMember( Long memberId);

}
