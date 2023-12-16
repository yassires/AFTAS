package com.youcode.aftas.Services;

import com.youcode.aftas.DTO.RankingDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RankingService {

    List<RankingDto> findAll();

    RankingDto registerMember(RankingDto rankingDto);
}
