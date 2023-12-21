package com.youcode.aftas.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RankingDto {

    private Integer rank;
    private Integer score;
    private CompetitionDto competition;
    private MemberDto member;
}