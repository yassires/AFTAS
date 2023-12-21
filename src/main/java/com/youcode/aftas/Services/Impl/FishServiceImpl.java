package com.youcode.aftas.Services.Impl;


import com.youcode.aftas.DTO.FishDto;
import com.youcode.aftas.DTO.LevelDto;
import com.youcode.aftas.DTO.MemberDto;
import com.youcode.aftas.Services.FishService;
import com.youcode.aftas.Services.LevelService;
import com.youcode.aftas.entities.Fish;
import com.youcode.aftas.entities.Level;
import com.youcode.aftas.entities.Member;
import com.youcode.aftas.handlers.exception.ResourceException;
import com.youcode.aftas.repository.FishRepository;
import com.youcode.aftas.repository.LevelRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FishServiceImpl implements FishService {

    private final FishRepository fishRepository;
    private final LevelService levelService;
    private final ModelMapper modelMapper;
    private final LevelRepository levelRepository;


    @Override
        public Fish getFishById(Long id) {
        return fishRepository.findById(id).orElseThrow(() -> new ResourceException("Fish id " + id + " not found"));
    }
    @Override
    public List<Fish> getAllFishes() {
        return fishRepository.findAll();
    }

    @Override
    public FishDto addFish(FishDto fishDto) {

        if(fishRepository.findByName(fishDto.getName()) != null) {
            throw new ResourceException("Fish name " + fishDto.getName() + " already exist");
        }

        Level level = levelRepository.findById(fishDto.getLevel().getId()).orElse(null);
        if(level == null) {
            throw new ResourceException("Level id " + fishDto.getLevel().getId() + " not found");
        }
        Fish fish = Fish.builder()
        .name(fishDto.getName())
        .averageWeight(fishDto.getAverageWeight())
        .level(level).
                build();
        Fish saved = fishRepository.save(fish);
        return modelMapper.map(saved, FishDto.class);

    }

    @Override
    public void deleteFish(Long id) {
        fishRepository.deleteById(id);
    }
}
