package com.youcode.aftas.Controllers;

import com.youcode.aftas.DTO.CompetitionDto;
import com.youcode.aftas.Services.CompetitionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/competition")
@RequiredArgsConstructor
public class CompetitionController {

    private final CompetitionService competitionService;


    @GetMapping("/{code}")
    public CompetitionDto getCompetitionByCode(@PathVariable("code") String code) {
        return competitionService.getCompetitionByCode(code);
    }
    @GetMapping
    public List<CompetitionDto> getAllCompetitions() {
        return competitionService.getAllCompetitions();
    }

    @PostMapping
    public CompetitionDto save(@Valid @RequestBody CompetitionDto competitionDto) {
        return competitionService.addCompetition(competitionDto);
    }



}
