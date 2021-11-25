package com.metawara.quickscore.model.match;

import com.metawara.quickscore.model.FootballClub;
import com.metawara.quickscore.model.roster.MatchRoster;

public class FCMatchStatistics {
    private final FootballClub footballClub;
    private MatchRoster matchRoster;
    private int goalsScored;

    public FCMatchStatistics(FootballClub footballClub, int goalsScored) {
        this.footballClub = footballClub;
        this.goalsScored = goalsScored;
    }

    public FCMatchStatistics(FootballClub footballClub, MatchRoster matchRoster, int goalsScored) {
        this.matchRoster = matchRoster;
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

    public MatchRoster getMatchRoster() {
        return matchRoster;
    }
}
