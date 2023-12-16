package com.youcode.aftas.Services;

import com.youcode.aftas.DTO.FishDto;
import com.youcode.aftas.entities.Fish;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FishService {
    Fish getFishById(Long id);
    List<Fish> getAllFishes();
    FishDto addFish(FishDto fishDto);
    void deleteFish(Long id);
}
