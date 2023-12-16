package com.youcode.aftas.Controllers;

import com.youcode.aftas.DTO.FishDto;
import com.youcode.aftas.Services.FishService;
import com.youcode.aftas.entities.Fish;
import com.youcode.aftas.handlers.response.ResponseMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fish")
@RequiredArgsConstructor
public class FishController {

    private final FishService fishService;
    private final ModelMapper modelMapper;


    @GetMapping("{id}")
    public ResponseEntity getFishById(@PathVariable Long id) {
        Fish fish = fishService.getFishById(id);
        if(fish == null) {
            return ResponseMessage.notFound("Fish not found");
        }else {
            return ResponseMessage.ok(fish, "Success");
        }
    }

    @GetMapping
    public ResponseEntity getAllFishes() {
        List<Fish> fishes = fishService.getAllFishes();
        if(fishes.isEmpty()) {
            return ResponseMessage.notFound("Fish not found");
        }else {
            return ResponseMessage.ok(fishes, "Success");
        }
    }

    @PostMapping
    public ResponseEntity addFish(@Valid @RequestBody FishDto fishDto) {
        FishDto fishDto1 = fishService.addFish(fishDto);
        if(fishDto1 == null) {
            return ResponseMessage.badRequest("Fish not created");
        }else {
            return ResponseMessage.created(fishDto1, "Fish created successfully");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteFish(@PathVariable Long id) {
        Fish fish = fishService.getFishById(id);
        if(fish == null) {
            return ResponseMessage.notFound("Fish not found");
        }else {
            fishService.deleteFish(id);
            return ResponseMessage.ok(null,"Fish deleted successfully");
        }
    }
}
