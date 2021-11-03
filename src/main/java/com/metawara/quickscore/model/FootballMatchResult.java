package com.metawara.quickscore.model;

public class FootballMatchResult {
    private FootballClubMatchStatistics homeSideStatistics;
    private FootballClubMatchStatistics awaySideStatistics;

    public FootballMatchResult(FootballClubMatchStatistics homeSideStatistics, FootballClubMatchStatistics awaySideStatistics) {
        this.homeSideStatistics = homeSideStatistics;
        this.awaySideStatistics = awaySideStatistics;
    }

    public FootballClubMatchStatistics getHomeSideMatchStatistics() {
        return homeSideStatistics;
    }

    public FootballClubMatchStatistics getAwaySideMatchStatistics() {
        return awaySideStatistics;
    }

    public void addHomeSideGoal() {
        homeSideStatistics.increaseGoalsScored();
    }

    public void addAwaySideGoal() {
        awaySideStatistics.increaseGoalsScored();
    }

    public String getHomeSideResult() {
        return homeSideStatistics.getFootballClub().getName() + " - " + homeSideStatistics.getGoalsScored();
    }

    public String getAwaySideResult() {
        return awaySideStatistics.getFootballClub().getName() + " - " + awaySideStatistics.getGoalsScored();
    }
}
