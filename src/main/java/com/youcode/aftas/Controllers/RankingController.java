package com.youcode.aftas.Controllers;

import com.youcode.aftas.DTO.RankingDto;
import com.youcode.aftas.Services.RankingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/ranking")
public class RankingController {

    private final RankingService rankingService;

    @GetMapping
    public List<RankingDto> getAll() {
        return rankingService.findAll();
    }

    @PostMapping
    public RankingDto registerMember(@Valid @RequestBody RankingDto rankingDto) {
        return rankingService.registerMember(rankingDto);
    }
}
