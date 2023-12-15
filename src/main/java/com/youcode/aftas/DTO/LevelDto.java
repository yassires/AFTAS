package com.youcode.aftas.DTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LevelDto implements Serializable {
    private Integer id;
    private Integer points;
    private String description;
}
