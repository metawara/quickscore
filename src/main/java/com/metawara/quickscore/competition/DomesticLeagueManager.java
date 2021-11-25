package com.metawara.quickscore.competition;

import com.metawara.quickscore.importer.FootballClubImporter;
import com.metawara.quickscore.match.manager.MatchManager;
import com.metawara.quickscore.model.FootballClub;
import com.metawara.quickscore.model.match.FCMatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class DomesticLeagueManager {

    private static final Logger logger = LoggerFactory.getLogger(DomesticLeagueManager.class);

    List<FootballClub> leagueClubs;
    Map<Integer, List<FCMatch>> matchWeekHistory;

    private int weekCounter = 0;

    private final MatchManager matchManager;

    public DomesticLeagueManager(MatchManager matchManager, FootballClubImporter importer) {
        this.matchManager = matchManager;
        leagueClubs = importer.importClubs();
        matchWeekHistory = new MatchScheduleGenerator().generateMatchWeeks(leagueClubs);
    }

    void simulateWeek() {
        if (isLeagueOver()) {
            logger.warn("All matches in the league have been simulated. Unable to simulate more matches.");
            return;
        }

        for (FCMatch match : matchWeekHistory.get(weekCounter)) {
            matchManager.manageMatch(match);
        }

        logger.debug("Match week {} finished.", weekCounter);
        weekCounter++;
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
