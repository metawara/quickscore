package com.metawara.quickscore.competition;

import com.metawara.quickscore.model.FootballClub;
import com.metawara.quickscore.model.match.FCMatch;
import com.metawara.quickscore.model.match.FCMatchStatistics;

import java.util.*;

public class StandingsManager {

    Map<String, Integer> standings;

    public StandingsManager(List<FootballClub> leagueClubs) {
        standings = new TreeMap<>();

        for (FootballClub footballClub : leagueClubs) {
            standings.put(footballClub.getName(), 0);
        }
    }

    public void updateFromMatch(FCMatch match) {
        if(matchFinishedInADraw(match)){
            putPoints(match.getHomeSideMatchStatistics(), 1);
            putPoints(match.getAwaySideMatchStatistics(), 1);
        } else if (homeSideWonMatch(match)) {
            putPoints(match.getHomeSideMatchStatistics(), 3);
        } else {
            putPoints(match.getAwaySideMatchStatistics(), 3);
        }
    }

    private void putPoints(FCMatchStatistics matchStatistics, int pointsAwarded) {
        standings.put(matchStatistics.getFootballClub().getName(), standings.get(matchStatistics.getFootballClub().getName()) + pointsAwarded);
    }

    private boolean matchFinishedInADraw(FCMatch match) {
        return match.getHomeSideMatchStatistics().getGoalsScored() == match.getAwaySideMatchStatistics().getGoalsScored();
    }

    private boolean homeSideWonMatch(FCMatch match) {
        return match.getHomeSideMatchStatistics().getGoalsScored() > match.getAwaySideMatchStatistics().getGoalsScored();
    }

    public Map<String, Integer> getSortedStandings() {
        Map<String, Integer> reverseSortedMap = new LinkedHashMap<>();
        standings.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
        return  reverseSortedMap;
    }

}
