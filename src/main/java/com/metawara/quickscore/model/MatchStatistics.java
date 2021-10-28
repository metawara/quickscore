package com.metawara.quickscore.model;

public class MatchStatistics {
    private final FootballClub footballClub;
    private final int goalsScored;

    public MatchStatistics(FootballClub footballClub, int goalsScored) {
        this.footballClub = footballClub;
        this.goalsScored = goalsScored;
    }

    public FootballClub getFootballClub() {
        return footballClub;
    }

    public int getGoalsScored() {
        return goalsScored;
    }
}
