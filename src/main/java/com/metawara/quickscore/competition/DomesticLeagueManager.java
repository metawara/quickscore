package com.metawara.quickscore.competition;

import com.metawara.quickscore.importer.FootballClubImporter;
import com.metawara.quickscore.match.manager.MatchManager;
import com.metawara.quickscore.model.FootballClub;
import com.metawara.quickscore.model.match.FCMatch;
import com.metawara.quickscore.model.match.MatchWeek;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Provides control of all actions related to league managing (automatic generation of league schedule
 * for provided clubs, triggering simulation of matches for a given week).
 */
public class DomesticLeagueManager {

    private static final Logger logger = LoggerFactory.getLogger(DomesticLeagueManager.class);

    List<FootballClub> participatingClubs;
    List<MatchWeek> matchWeekHistory;

    private int weekCounter = 0;

    private final MatchManager matchManager;
    StandingsManager standingsManager;

    public DomesticLeagueManager(MatchManager matchManager, FootballClubImporter importer) {
        this.matchManager = matchManager;
        participatingClubs = importer.importClubs();
        standingsManager = new StandingsManager(participatingClubs);
        matchWeekHistory = MatchScheduleGenerator.generateMatchWeeksForDoubleRoundRobinTrmt(participatingClubs);
    }

    public void simulateWeek() {
        if (isLeagueOver()) {
            logger.warn("All matches in the league have been simulated. Unable to simulate more matches.");
            return;
        }
        weekCounter++;
        Optional<MatchWeek> currentWeek = matchWeekHistory.stream().filter(x -> x.getWeekNumber() == weekCounter).findAny();
        MatchWeek cw = currentWeek.orElse(new MatchWeek(0, new ArrayList<>()));
        for (FCMatch match : cw.getWeekMatches()) {
            matchManager.manageMatch(match);
            standingsManager.updateFromMatch(match);
        }

        logger.debug("Match week {} finished.", weekCounter);
        logger.debug("Standings after week {}: {}.", weekCounter, standingsManager.getSortedStandings());

    }

    private boolean isLeagueOver() {
        return weekCounter > participatingClubs.size() * 2 - 3;
    }

    public int getWeekCounter() {
        return weekCounter;
    }

    public List<MatchWeek> getMatchWeekHistory() {
        return matchWeekHistory;
    }
}
