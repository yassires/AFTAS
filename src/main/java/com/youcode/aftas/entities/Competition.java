package com.youcode.aftas.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Competition {
    @Id
    private String code;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    private int numberOfParticipants;


    private String location;


    private Double amount;



    @OneToMany(mappedBy = "competition")
    @ToString.Exclude
    @JsonIgnore
    private List<Ranking> rankings;

    @OneToMany(mappedBy = "competition")
    @ToString.Exclude
    @JsonIgnore
    private List<Hunting> hunting;


}
