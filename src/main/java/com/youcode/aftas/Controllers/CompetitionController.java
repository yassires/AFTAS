package com.youcode.aftas.Controllers;

import com.youcode.aftas.DTO.CompetitionDto;
import com.youcode.aftas.DTO.RankingDto;
import com.youcode.aftas.Services.CompetitionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/competition")
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

    @GetMapping("/score/{code}")
    public List<RankingDto> calculateScore(@PathVariable String code){
        return competitionService.calculateScore(code);
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<CompetitionDto>> getAllCompetitionsPaged(Pageable pageable) {
        Page<CompetitionDto> competitions = competitionService.getAllCompetitions(pageable);
        return new ResponseEntity<>(competitions, HttpStatus.OK);
    }


}
