package com.metawara.quickscore.competition;

import com.metawara.quickscore.model.FootballClub;
import com.metawara.quickscore.model.TableResults;
import com.metawara.quickscore.model.match.FCMatch;
import com.metawara.quickscore.model.match.FCMatchStatistics;

import java.util.*;
import java.util.stream.Collectors;

public class StandingsManager {

    List<TableResults> tableResults = new ArrayList<>();

    public StandingsManager(List<FootballClub> leagueClubs) {
        for (FootballClub club : leagueClubs) {
            tableResults.add(new TableResults(club));
        }
    }

    public void updateFromMatch(FCMatch match) {
        int homeSidePoints = 0;
        int awaySidePoints = 0;
        if (matchFinishedInADraw(match)) {
            homeSidePoints = 1;
            awaySidePoints = 1;
        } else if (homeSideWonMatch(match)) {
            homeSidePoints = 3;
        } else {
            awaySidePoints = 3;
        }
        updateStandingsForClub(match.getHomeSideMatchStatistics(), homeSidePoints);
        updateGBForClub(match.getHomeSideMatchStatistics(), match.getAwaySideMatchStatistics().getGoalsScored());

        updateStandingsForClub(match.getAwaySideMatchStatistics(), awaySidePoints);
        updateGBForClub(match.getAwaySideMatchStatistics(), match.getHomeSideMatchStatistics().getGoalsScored());
    }

    private void updateGBForClub(FCMatchStatistics matchStatistics, int goalsConceded) {
        TableResults results = findResults(matchStatistics);

        results.updateGoalsScored(matchStatistics.getGoalsScored());
        results.updateGoalsConceded(goalsConceded);
    }

    private void updateStandingsForClub(FCMatchStatistics matchStatistics, int points) {
        TableResults results = findResults(matchStatistics);

        results.increaseMatchesPlayed();
        results.updatePoints(points);
    }

    private TableResults findResults(FCMatchStatistics matchStatistics) {
        return tableResults.stream().filter(x -> x.getFootballClub().getName().equals(matchStatistics.getFootballClub().getName()))
                .collect(Collectors.toList()).get(0);
    }


    private boolean matchFinishedInADraw(FCMatch match) {
        return match.getHomeSideMatchStatistics().getGoalsScored() == match.getAwaySideMatchStatistics().getGoalsScored();
    }

    private boolean homeSideWonMatch(FCMatch match) {
        return match.getHomeSideMatchStatistics().getGoalsScored() > match.getAwaySideMatchStatistics().getGoalsScored();
    }

    public List<TableResults> getSortedStandings() {
        return tableResults.stream()
                .sorted(Comparator.comparing(TableResults::getPoints).reversed())
                .collect(Collectors.toList());
    }
}
