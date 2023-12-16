package com.youcode.aftas.Services.Impl;

import com.youcode.aftas.DTO.CompetitionDto;
import com.youcode.aftas.Services.CompetitionService;
import com.youcode.aftas.entities.Competition;
import com.youcode.aftas.entities.Ranking;
import com.youcode.aftas.handlers.exception.OperationException;
import com.youcode.aftas.handlers.exception.ResourceException;
import com.youcode.aftas.repository.CompetitionRepository;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CompetitionServiceImpl implements CompetitionService {

    private final CompetitionRepository competitionRepository;
    private final ModelMapper mapper;
    @Override
    public CompetitionDto getCompetitionByCode(String code) {
        String codeFormat = code.substring(0, 3).toLowerCase();
        String code1 = competitionRepository.findByCode(codeFormat).getCode();
        if (code1 == null) {
            throw new ResourceException("Competition code " + code + " not found");
        }else{
            return mapper.map(competitionRepository.findByCode(codeFormat), CompetitionDto.class);
        }

    }

    @Override
    public List<CompetitionDto> getAllCompetitions() {

        return competitionRepository.findAll().stream().map(competition -> {
            return mapper.map(competition, CompetitionDto.class);
        }).collect(Collectors.toList());
    }

    @Override
    public CompetitionDto addCompetition(CompetitionDto competitionDto) {

        Competition competition = competitionRepository.findByDate(competitionDto.getDate());
        if (competition != null) {
            throw new OperationException("There is already a competition on this date :" + competition.getDate());
        }
        if (competitionDto.getStartTime().isAfter(competitionDto.getEndTime())) {
            throw new OperationException("Start time must be before end time");
        }

        String code = CodeFormat(competitionDto.getLocation(), competitionDto.getDate());
        competitionDto.setCode(code);

        Competition competition1 = mapper.map(competitionDto, Competition.class);
        Competition saved = competitionRepository.save(competition1);
        return mapper.map(saved, CompetitionDto.class);
    }



    public static String CodeFormat(String location, LocalDate date) {
        String locationCode = location.substring(0, 3).toLowerCase();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yy-MM-dd");
        String formattedDate = date.format(dateFormatter);

        return locationCode + "-" + formattedDate;
    }

}
