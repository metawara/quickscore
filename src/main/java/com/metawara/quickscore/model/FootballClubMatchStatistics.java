package com.metawara.quickscore.model;

public class FootballClubMatchStatistics {
    private final FootballClub footballClub;
    private int goalsScored;

    public FootballClubMatchStatistics(FootballClub footballClub, int goalsScored) {
        this.footballClub = footballClub;
        this.goalsScored = goalsScored;
    }

    public FootballClub getFootballClub() {
        return footballClub;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void increaseGoalsScored() {
        goalsScored++;
    }
}
