package com.youcode.aftas.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LevelDto implements Serializable {


    private Integer id;

    @NotNull(message = "Points cannot be null.")
    private Integer points;

    @NotNull(message = "Description cannot be null.")
    private String description;
}
