package com.youcode.aftas.Services;

import com.youcode.aftas.entities.Level;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LevelService {
    Level getLevelById(Integer id);
    List<Level> getAllLevels();
    Level addLevel(Level level);
    void deleteLevel(Integer id);

}
