package com.metawara.quickscore.competition;

import com.metawara.quickscore.model.FootballClub;
import com.metawara.quickscore.model.match.FCMatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MatchScheduleGenerator {

    private static final Logger logger = LoggerFactory.getLogger(MatchScheduleGenerator.class);

    Map<Integer, List<Integer>> matchups;

    Random rand = new Random();

    Map<Integer, List<FCMatch>> generateMatchWeeks(List<FootballClub> leagueClubs) {
        Map<Integer, List<FCMatch>> matchWeekHistory = new HashMap<>();
        initializeMatchups(leagueClubs, matchWeekHistory);

        int internalWeekCounter = 1;
        List<FCMatch> matchesForCurrentWeek = new ArrayList<>();

        while (internalWeekCounter <= leagueClubs.size() * 2 - 2) {
            int[][] weeklyMatchupIds = generateMatchupsForAWeek(leagueClubs);

            if (weeklyMatchupIds.length == 0) {
                logger.debug("Encountered a match-making deadlock. Reverting to the beginning...");
                initializeMatchups(leagueClubs, matchWeekHistory);
                internalWeekCounter = 1;
            } else {
                for (int[] matchup : weeklyMatchupIds) {
                    matchups.get(matchup[0]).remove(Integer.valueOf(matchup[1]));
                    matchesForCurrentWeek.add(new FCMatch(leagueClubs.get(matchup[0] - 1), leagueClubs.get(matchup[1] - 1)));
                }
                matchWeekHistory.put(internalWeekCounter, new ArrayList<>(matchesForCurrentWeek));
                matchesForCurrentWeek.clear();
                internalWeekCounter++;
            }
        }
        return matchWeekHistory;
    }

    /**
     * This algorithm NEEDS a change in the future.
     */
    private int[][] generateMatchupsForAWeek(List<FootballClub> leagueClubs) {
        List<Integer> availableClubsToPickFrom = IntStream.rangeClosed(1, leagueClubs.size()).boxed().collect(Collectors.toList());
        int[][] pairs = new int[leagueClubs.size() / 2][2];
        int pairsFound = 0;

        while (!availableClubsToPickFrom.isEmpty()) {
            int homeSideId = pickRandomClub(availableClubsToPickFrom);
            availableClubsToPickFrom.remove(Integer.valueOf(homeSideId));

            List<Integer> homeSideAvailableMatchups = matchups.get(homeSideId);

            int retries = 0;
            while (homeSideAvailableMatchups.isEmpty()) {
                homeSideId = swapHomeSideId(availableClubsToPickFrom, homeSideId);

                homeSideAvailableMatchups = matchups.get(homeSideId);
                retries++;
                if (retries > 10) {
                    return new int[0][0];
                }
            }

            int awaySideId = pickRandomClub(homeSideAvailableMatchups);

            if (!availableClubsToPickFrom.remove(Integer.valueOf(awaySideId))) {
                pairs = new int[leagueClubs.size() / 2][2];
                pairsFound = 0;
                availableClubsToPickFrom = IntStream.rangeClosed(1, leagueClubs.size()).boxed().collect(Collectors.toList());
            } else {
                savePair(pairs, pairsFound, homeSideId, awaySideId);
                pairsFound++;
            }
        }

        return pairs;
    }

    private void savePair(int[][] pairs, int pairsFound, int homeSideId, int awaySideId) {
        pairs[pairsFound][0] = homeSideId;
        pairs[pairsFound][1] = awaySideId;
    }

    private int swapHomeSideId(List<Integer> availableClubsToPickFrom, int homeSideId) {
        int buf = homeSideId;
        homeSideId = pickRandomClub(availableClubsToPickFrom);
        availableClubsToPickFrom.remove(Integer.valueOf(homeSideId));
        availableClubsToPickFrom.add(buf);
        return homeSideId;
    }

    private Integer pickRandomClub(List<Integer> availableClubsToPickFrom) {
        return availableClubsToPickFrom.get(rand.nextInt(availableClubsToPickFrom.size()));
    }

    private void initializeMatchups(List<FootballClub> leagueClubs, Map<Integer, List<FCMatch>> matchWeekHistory) {
        matchups = new HashMap<>();
        matchWeekHistory.clear();
        for (int a = 1; a < leagueClubs.size() + 1; a++) {
            List<Integer> specificMatchups = IntStream.rangeClosed(1, leagueClubs.size()).boxed().collect(Collectors.toList());
            specificMatchups.remove(a - 1);
            matchups.put(a, specificMatchups);
        }
        logger.debug("Matchups initialized for league size: {}.", leagueClubs.size());
    }

}
