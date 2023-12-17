package com.youcode.aftas.Controllers;

import com.youcode.aftas.DTO.HuntingDto;
import com.youcode.aftas.Services.HuntingService;
import com.youcode.aftas.entities.Hunting;
import com.youcode.aftas.handlers.response.ResponseMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hunting")
@RequiredArgsConstructor
public class HuntingController {

    private final HuntingService huntingService;

    public ResponseEntity getAllHunting() {
        HuntingDto hunting = (HuntingDto) huntingService.getAllHunting();
        if(hunting == null) {
            return ResponseMessage.badRequest("There's no Hunting in the records");
        }else {
            return ResponseMessage.ok(hunting,"Success");
        }
    }

    public ResponseEntity getHuntingByCompetition(@PathVariable Long competitionId) {
        HuntingDto hunting = (HuntingDto) huntingService.getHuntingsByCompetition(competitionId);
        if(hunting == null) {
            return ResponseMessage.badRequest("There's no Hunting in the records");
        }else {
            return ResponseMessage.ok(hunting,"Success");
        }
    }
    public ResponseEntity getHuntingByMember(@PathVariable Long memberId) {
        HuntingDto hunting = (HuntingDto) huntingService.getHuntingsByMember(memberId);
        if(hunting == null) {
            return ResponseMessage.badRequest("There's no Hunting in the records");
        }else {
            return ResponseMessage.ok(hunting,"Success");
        }
    }

    public ResponseEntity addHuntingResult(@Valid @RequestBody HuntingDto huntingDto) {
        HuntingDto hunting = huntingService.addHuntingResult(huntingDto);
        if(hunting == null) {
            return ResponseMessage.badRequest("Hunting result not added");
        }else {
            return ResponseMessage.created(hunting,"Hunting result added successfully");
        }
    }


}
