package com.metawara.quickscore.model.match;

public class Match {
    private FCMatchStatistics homeSideStatistics;
    private FCMatchStatistics awaySideStatistics;

    public Match(FCMatchStatistics homeSideStatistics, FCMatchStatistics awaySideStatistics) {
        this.homeSideStatistics = homeSideStatistics;
        this.awaySideStatistics = awaySideStatistics;
    }

    public FCMatchStatistics getHomeSideMatchStatistics() {
        return homeSideStatistics;
    }

    public FCMatchStatistics getAwaySideMatchStatistics() {
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

    public double getHomeSideWinningChance() {
        return homeSideStatistics.getFootballClub().getChanceOfWinning();
    }

    public double getAwaySideWinningChance() {
        return awaySideStatistics.getFootballClub().getChanceOfWinning();
    }

}
