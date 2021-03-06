package com.metawara.quickscore.model.match;

import com.metawara.quickscore.model.FootballClub;

public class FCMatch {
    private final FCMatchStatistics homeSideStatistics;
    private final FCMatchStatistics awaySideStatistics;

    public FCMatch(FootballClub homeSide, FootballClub awaySide){
        this.homeSideStatistics = new FCMatchStatistics(homeSide, 0);
        this.awaySideStatistics = new FCMatchStatistics(awaySide, 0);
    }

    public FCMatch(FCMatchStatistics homeSideStatistics, FCMatchStatistics awaySideStatistics) {
        this.homeSideStatistics = homeSideStatistics;
        this.awaySideStatistics = awaySideStatistics;
    }

    @Override
    public String toString() {
        return "FCMatch{" +
                "homeSideStatistics=" + homeSideStatistics +
                ", awaySideStatistics=" + awaySideStatistics +
                '}';
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
        return homeSideStatistics.getFootballClub().getBaseWinningChance();
    }

    public double getAwaySideWinningChance() {
        return awaySideStatistics.getFootballClub().getBaseWinningChance();
    }

}
