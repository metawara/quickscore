package com.metawara.quickscore.model;

public class FootballMatchResult {
    private MatchStatistics homeSideMatchStatistics;
    private MatchStatistics awaySideMatchStatistics;

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
}
