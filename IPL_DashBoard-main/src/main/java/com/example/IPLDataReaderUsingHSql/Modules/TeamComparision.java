package com.example.IPLDataReaderUsingHSql.Modules;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamComparision {
    private int TotalMatch;
    private int MatchWonByTeam1;
    private int MatchWonByTeam2;
    private int NoResult;
    private int TossByTeam1;
    private int TossWonByTeam2;



}
