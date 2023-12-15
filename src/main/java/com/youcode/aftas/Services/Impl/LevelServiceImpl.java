package com.youcode.aftas.Services.Impl;

import com.youcode.aftas.Services.LevelService;
import com.youcode.aftas.entities.Level;
import com.youcode.aftas.handlers.exception.OperationException;
import com.youcode.aftas.handlers.exception.ResourceException;
import com.youcode.aftas.repository.LevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LevelServiceImpl implements LevelService {

    private final LevelRepository levelRepository;
    @Override
    public Level getLevelById(Integer id) {
        return levelRepository.findById(id).orElseThrow(() -> new ResourceException("Level id " + id + " not found"));
    }

    @Override
    public List<Level> getAllLevels() {
        return levelRepository.findAll();
    }

    @Override
    public Level addLevel(Level level) {
        Level levels = levelRepository.findAll().stream().max((l1, l2) -> l1.getPoints() > l2.getPoints() ? 1 : -1).orElse(null);
        if(levels != null) {
            if(level.getPoints() <= levels.getPoints()) {
                throw new OperationException("Point must be greater than " + levels.getPoints());
            }
        }
        return levelRepository.save(level);    }


    @Override
    public void deleteLevel(Integer id) {
        levelRepository.deleteById(id);
    }
}
