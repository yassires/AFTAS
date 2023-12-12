package com.youcode.aftas.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Competition {
    @Id
    private String code;
    private String date;
    private String startTime;
    private String endTime;
    private int numberOfParticipants;
    private String location;
    private int amountOfFish;
    @OneToMany(mappedBy = "competition")
    private List<Ranking> rankings;
}
