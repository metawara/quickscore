package com.metawara.quickscore.competition;

import com.metawara.quickscore.model.FootballClub;
import com.metawara.quickscore.model.MonoPair;
import com.metawara.quickscore.model.match.FCMatch;
import com.metawara.quickscore.model.match.MatchWeek;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A schedule generator for various types of competitions.
 * It makes use of tournament system generators to provide more specific types of tournaments.
 */
public class MatchScheduleGenerator {

    private static final Logger logger = LoggerFactory.getLogger(MatchScheduleGenerator.class);

    private MatchScheduleGenerator() {
    }

    public static List<MatchWeek> generateMatchWeeksForDoubleRoundRobinTrmt(List<FootballClub> leagueClubs) {
        if (leagueClubs.size() % 2 == 1) {
            logger.warn("Unable to generate match weeks for odd amount of clubs. Aborting...");
            return Collections.emptyList();
        }
        List<List<MonoPair<Integer>>> roundRobinMatchups = TournamentSystemGenerator.generateRoundRobinMatchups(leagueClubs.size());

        List<List<MonoPair<Integer>>> invertedRoundRobinMatchups = new ArrayList<>();

        roundRobinMatchups.forEach(matchWeekMatchup -> {
            List<MonoPair<Integer>> invertedMatchWeekMatchup = new ArrayList<>();

            matchWeekMatchup.forEach(pair -> invertedMatchWeekMatchup.add(pair.invert()));
            invertedRoundRobinMatchups.add(invertedMatchWeekMatchup);
        });

        roundRobinMatchups.addAll(invertedRoundRobinMatchups);

        int internalWeekCounter = 1;
        List<FCMatch> matchesForCurrentWeek = new ArrayList<>();

        List<MatchWeek> matchWeekHistory = new ArrayList<>();
        for (List<MonoPair<Integer>> listOfPairs : roundRobinMatchups) {
            for (MonoPair<Integer> pair : listOfPairs) {
                matchesForCurrentWeek.add(new FCMatch(leagueClubs.get(pair.getA()), leagueClubs.get(pair.getB())));
            }
            matchWeekHistory.add(new MatchWeek(internalWeekCounter++, new ArrayList<>(matchesForCurrentWeek)));
            matchesForCurrentWeek = new ArrayList<>();
        }
        logger.debug("Generated match weeks.");

        Collections.shuffle(matchWeekHistory);

        return matchWeekHistory;
    }

}
