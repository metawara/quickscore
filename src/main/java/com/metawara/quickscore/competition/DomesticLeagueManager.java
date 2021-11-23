package com.metawara.quickscore.competition;

import com.metawara.quickscore.importer.FootballClubImporter;
import com.metawara.quickscore.match.manager.MatchManager;
import com.metawara.quickscore.model.FootballClub;
import com.metawara.quickscore.model.match.FCMatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DomesticLeagueManager {

    private static final Logger logger = LoggerFactory.getLogger(DomesticLeagueManager.class);

    private final int MAXIMUM_AMOUNT_OF_MATCHES;

    Map<Integer, List<Integer>> matchups;
    List<FootballClub> leagueClubs;
    Map<Integer, List<FCMatch>> matchWeekHistory;
    Random rand = new Random();

    private int weekCounter = 0;

    private final MatchManager matchManager;

    public DomesticLeagueManager(MatchManager matchManager, FootballClubImporter importer) {
        this.matchManager = matchManager;
        leagueClubs = importer.importClubs();
        MAXIMUM_AMOUNT_OF_MATCHES = leagueClubs.size() * 2 - 2;
        generateMatchWeeks();
    }

    private void generateMatchWeeks() {
        generateMatchups();

        int internalWeekCounter = 1;
        List<FCMatch> matchesForCureentWeek = new ArrayList<>();
        while(internalWeekCounter <= leagueClubs.size() * 2 - 2){

            logger.debug("Generating fixtures for week {}", internalWeekCounter);
            int[][] pairs = generatePairs();

            if (pairs.length == 0) {
                logger.debug("Resetting generation...");
                generateMatchups();
                internalWeekCounter = 1;
            } else {
                for (int[] pair : pairs) {
                    matchups.get(pair[0]).remove(Integer.valueOf(pair[1]));
                    matchesForCureentWeek.add(new FCMatch(leagueClubs.get(pair[0] - 1), leagueClubs.get(pair[1] - 1)));
                }
                matchWeekHistory.put(internalWeekCounter, new ArrayList<>(matchesForCureentWeek));
                matchesForCureentWeek.clear();
                internalWeekCounter++;
            }
        }

    }

    void simulateWeek() {
        if (isLeagueOver()) {
            logger.warn("Maximum amount of match weeks had been reached. Unable to generate more matches.");
            return;
        } else {
            weekCounter++;
        }

        for (FCMatch match : matchWeekHistory.get(weekCounter)) {
            matchManager.manageMatch(match);
        }

        logger.debug("Match week {} finished.", weekCounter);
    }

    private boolean isLeagueOver() {
        return weekCounter > MAXIMUM_AMOUNT_OF_MATCHES;
    }

    private int[][] generatePairs() {
        List<Integer> availableClubsToPickFrom = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());
        int[][] pairs = new int[leagueClubs.size() / 2][2];
        int pairsFound = 0;

        while (!availableClubsToPickFrom.isEmpty()) {
            int homeSideId = availableClubsToPickFrom.get(rand.nextInt(availableClubsToPickFrom.size()));
            availableClubsToPickFrom.remove(Integer.valueOf(homeSideId));

            List<Integer> homeSideAvailableMatchups = matchups.get(homeSideId);

            int retries = 0;
            while (homeSideAvailableMatchups.isEmpty()) {
                int buf = homeSideId;
                homeSideId = availableClubsToPickFrom.get(rand.nextInt(availableClubsToPickFrom.size()));
                availableClubsToPickFrom.remove(Integer.valueOf(homeSideId));
                availableClubsToPickFrom.add(buf);

                homeSideAvailableMatchups = matchups.get(homeSideId);
                retries++;
                if (retries > 20) {
                    logger.debug("Couldn't find pairings...");
                    return new int[0][0];
                }
            }
            int awaySideId = homeSideAvailableMatchups.get(rand.nextInt(homeSideAvailableMatchups.size()));

            if (!availableClubsToPickFrom.remove(Integer.valueOf(awaySideId))) {
                pairs = new int[leagueClubs.size() / 2][2];
                pairsFound = 0;
                availableClubsToPickFrom = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());
            } else {
                pairs[pairsFound][0] = homeSideId;
                pairs[pairsFound][1] = awaySideId;
                pairsFound++;
            }
        }

        String pairsStr = Arrays.deepToString(pairs);
        logger.debug("Found pairs: {}", pairsStr);
        return pairs;
    }

    private void generateMatchups() {
        matchups = new HashMap<>();
        matchWeekHistory = new HashMap<>();
        for (int a = 1; a < leagueClubs.size() + 1; a++) {
            List<Integer> specificMatchups = IntStream.rangeClosed(1, leagueClubs.size()).boxed().collect(Collectors.toList());
            specificMatchups.remove(a - 1);
            matchups.put(a, specificMatchups);
        }
    }

    public int getWeekCounter() {
        return weekCounter;
    }

    public Map<Integer, List<FCMatch>> getMatchWeekHistory() {
        return matchWeekHistory;
    }
}
