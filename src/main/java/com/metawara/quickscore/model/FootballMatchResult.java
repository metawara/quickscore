package com.metawara.quickscore.model;

public class FootballMatchResult {
    private final MatchStatistics homeSideMatchStatistics;
    private final MatchStatistics awaySideMatchStatistics;

    public FootballMatchResult(MatchStatistics homeSideMatchStatistics, MatchStatistics awaySideMatchStatistics) {
        this.homeSideMatchStatistics = homeSideMatchStatistics;
        this.awaySideMatchStatistics = awaySideMatchStatistics;
    }

    public MatchStatistics getHomeSideMatchStatistics() {
        return homeSideMatchStatistics;
    }

    public MatchStatistics getAwaySideMatchStatistics() {
        return awaySideMatchStatistics;
    }

    public String getHomeSideResult(){
        return homeSideMatchStatistics.getFootballClub().getName() + " - " + homeSideMatchStatistics.getGoalsScored();
    }

    public String getAwaySideResult(){
        return awaySideMatchStatistics.getFootballClub().getName() + " - " + awaySideMatchStatistics.getGoalsScored();
    }
}
