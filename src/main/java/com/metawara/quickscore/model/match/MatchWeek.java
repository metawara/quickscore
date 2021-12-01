package com.metawara.quickscore.model.match;

import java.util.List;

public class MatchWeek {
    private final int weekNumber;
    private List<FCMatch> weekMatches;

    public MatchWeek(int weekNumber, List<FCMatch> weekMatches) {
        this.weekNumber = weekNumber;
        this.weekMatches = weekMatches;
    }

    public void setWeekMatches(List<FCMatch> weekMatches) {
        this.weekMatches = weekMatches;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public List<FCMatch> getWeekMatches() {
        return weekMatches;
    }
}
