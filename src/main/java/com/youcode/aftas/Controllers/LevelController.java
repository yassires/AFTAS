package com.youcode.aftas.Controllers;

import com.youcode.aftas.Services.LevelService;
import com.youcode.aftas.entities.Level;
import com.youcode.aftas.handlers.response.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/level")
public class LevelController {

    private final LevelService levelService;

    @GetMapping("/{id}")
    public ResponseEntity getLevelById(@PathVariable Integer id){
        Level level = levelService.getLevelById(id);

        if (level == null) {
            return ResponseMessage.notFound("Level not found");
        }else {
            return ResponseMessage.ok(level,"Level found");
        }
    }

    @GetMapping
    public ResponseEntity getAllLevels() {
        List<Level> levels = levelService.getAllLevels();
        if(levels.isEmpty()) {
            return ResponseMessage.notFound("Levels not found");
        }else {
            return ResponseMessage.ok(levels, "Levels ");
        }
    }

    @PostMapping
    public ResponseEntity addLevel(@RequestBody Level level) {
        Level level1 = levelService.addLevel(level);
        if(level1 == null) {
            return ResponseMessage.badRequest("Level not created");
        }else {
            return ResponseMessage.created(level1, "Level created successfully");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteLevel(@PathVariable Integer id) {
        Level level = levelService.getLevelById(id);
        if(level == null) {
            return ResponseMessage.notFound("Level not found");
        }else {
            levelService.deleteLevel(id);
            return ResponseMessage.ok(null,"Level deleted successfully");
        }
    }
}
