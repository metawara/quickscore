package com.metawara.quickscore.competition;

import com.metawara.quickscore.importer.FootballClubImporter;
import com.metawara.quickscore.match.manager.MatchManager;
import com.metawara.quickscore.model.FootballClub;
import com.metawara.quickscore.model.match.FCMatch;
import com.metawara.quickscore.model.match.MatchWeek;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Provides control of all actions related to league managing (automatic generation of league schedule
 * for provided clubs, triggering simulation of matches for a given week).
 */
public class DomesticLeagueManager {

    private static final Logger logger = LoggerFactory.getLogger(DomesticLeagueManager.class);
    private final MatchManager matchManager;
    List<FootballClub> participatingClubs = Collections.emptyList();
    List<MatchWeek> matchWeekHistory = Collections.emptyList();
    StandingsManager standingsManager;
    private int weekCounter = 0;

    public DomesticLeagueManager(MatchManager matchManager) {
        this.matchManager = matchManager;
    }

    public void initialize(FootballClubImporter importer) {
        importClubs(importer);
        if (participatingClubs.isEmpty()) {
            logger.warn("Participating clubs list is empty after import attempt. Will not attempt to generate match weeks nor create a Standings Manager.");
        } else {
            standingsManager = new StandingsManager(participatingClubs);
            matchWeekHistory = MatchScheduleGenerator.generateMatchWeeksForDoubleRoundRobinTrmt(participatingClubs);
        }
    }

    private void importClubs(FootballClubImporter importer) {
        logger.debug("Attempting to import clubs");
        participatingClubs = importer.importClubs();
    }

    public void simulateWeek() {
        if (isLeagueOver()) {
            logger.warn("All matches in the league have been simulated. Unable to simulate more matches.");
            return;
        }
        weekCounter++;

        MatchWeek cw = matchWeekHistory.stream()
                .filter(x -> x.getWeekNumber() == weekCounter)
                .findAny()
                .orElse(new MatchWeek(0, new ArrayList<>()));
        for (FCMatch match : cw.getWeekMatches()) {
            matchManager.manageMatch(match);
            logger.debug("Match finished. Updating standings...");
            standingsManager.awardPoints(match);
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
