package com.metawara.quickscore.competition;

import com.metawara.quickscore.importer.FootballClubImporter;
import com.metawara.quickscore.match.manager.MatchManager;
import com.metawara.quickscore.model.FootballClub;
import com.metawara.quickscore.model.match.FCMatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Provides control of all actions related to league managing (automatic generation of league schedule
 * for provided clubs, triggering simulation of matches for a given week).
 */
public class DomesticLeagueManager {

    private static final Logger logger = LoggerFactory.getLogger(DomesticLeagueManager.class);

    List<FootballClub> leagueClubs;
    Map<Integer, List<FCMatch>> matchWeekHistory;

    private int weekCounter = 0;

    private final MatchManager matchManager;
    StandingsManager standingsManager;

    public DomesticLeagueManager(MatchManager matchManager, FootballClubImporter importer) {
        this.matchManager = matchManager;
        leagueClubs = importer.importClubs();
        standingsManager = new StandingsManager(leagueClubs);
        matchWeekHistory = new MatchScheduleGenerator().generateMatchWeeks(leagueClubs);
    }

    public void simulateWeek() {
        if (isLeagueOver()) {
            logger.warn("All matches in the league have been simulated. Unable to simulate more matches.");
            return;
        }
        weekCounter++;
        for (FCMatch match : matchWeekHistory.get(weekCounter)) {
            matchManager.manageMatch(match);
            standingsManager.updateFromMatch(match);
        }

        logger.debug("Match week {} finished.", weekCounter);
        logger.debug("Standings after week {}: \n\n{}.", weekCounter, standingsManager.getSortedStandings());

    }

    private boolean isLeagueOver() {
        return weekCounter > leagueClubs.size() * 2 - 2;
    }

    public int getWeekCounter() {
        return weekCounter;
    }

    public Map<Integer, List<FCMatch>> getMatchWeekHistory() {
        return matchWeekHistory;
    }
}
