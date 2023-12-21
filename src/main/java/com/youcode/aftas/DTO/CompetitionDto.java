package com.youcode.aftas.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class CompetitionDto implements Serializable {

    @NotNull(message = "code cannot be null.")
    @NotBlank(message = "code cannot be blank.")
    private String code;

    @NotNull(message = "Date cannot be null.")
    private LocalDate date;

    @NotNull(message = "startTime cannot be null.")
    private LocalTime startTime;

    @NotNull(message = "endTime cannot be null.")
    private LocalTime endTime;

    @NotNull(message = "location cannot be null.")
    @NotBlank(message = "location cannot be blank.")
    private String location;

    @NotNull(message = "amount cannot be null.")
    private Double amount;
}
