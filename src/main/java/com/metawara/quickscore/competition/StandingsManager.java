package com.metawara.quickscore.competition;

import com.metawara.quickscore.model.FootballClub;
import com.metawara.quickscore.model.match.FCMatch;
import com.metawara.quickscore.model.match.FCMatchStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Manages standings and awards points based on the match result.
 */
public class StandingsManager {

    private static final Logger logger = LoggerFactory.getLogger(StandingsManager.class);

    private int pointsForDraw = 1;
    private int pointsForVictory = 3;

    Map<String, Integer> standingsMap;

    public StandingsManager(List<FootballClub> leagueClubs) {
        standingsMap = new HashMap<>();
        leagueClubs.forEach(club -> standingsMap.put(club.getName(), 0));
    }

    public StandingsManager(Map<String, Integer> standingsMap, int pointsForDraw, int pointsForVictory) {
        this.pointsForDraw = pointsForDraw;
        this.pointsForVictory = pointsForVictory;
        this.standingsMap = standingsMap;
    }

    public void awardPoints(FCMatch match) {
        if(matchFinishedInADraw(match)){
            logger.debug("Match finished with a draw. Awarding {} point for both teams.", pointsForDraw);
            putPoints(match.getHomeSideMatchStatistics(), pointsForDraw);
            putPoints(match.getAwaySideMatchStatistics(), pointsForDraw);
        } else if (homeSideWonMatch(match)) {
            logger.debug("Match finished with a victory to a home side. Awarding {} points.", pointsForVictory);
            putPoints(match.getHomeSideMatchStatistics(), pointsForVictory);
        } else {
            logger.debug("Match finished with a victory to a away side. Awarding {} points.", pointsForVictory);
            putPoints(match.getAwaySideMatchStatistics(), pointsForVictory);
        }
    }

    private void putPoints(FCMatchStatistics matchStatistics, int pointsAwarded) {
        standingsMap.put(matchStatistics.getFootballClub().getName(),
                standingsMap.get(matchStatistics.getFootballClub().getName()) + pointsAwarded);
    }

    private boolean matchFinishedInADraw(FCMatch match) {
        return match.getHomeSideMatchStatistics().getGoalsScored() == match.getAwaySideMatchStatistics().getGoalsScored();
    }

    private boolean homeSideWonMatch(FCMatch match) {
        return match.getHomeSideMatchStatistics().getGoalsScored() > match.getAwaySideMatchStatistics().getGoalsScored();
    }

    public Map<String, Integer> getSortedStandings() {
        Map<String, Integer> sortedStandings = new LinkedHashMap<>();
        standingsMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> sortedStandings.put(x.getKey(), x.getValue()));
        return sortedStandings;
    }

}
