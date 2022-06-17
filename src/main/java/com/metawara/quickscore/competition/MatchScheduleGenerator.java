package com.metawara.quickscore.competition;

import com.metawara.quickscore.model.FootballClub;
import com.metawara.quickscore.model.MonoPair;
import com.metawara.quickscore.model.match.FCMatch;
import com.metawara.quickscore.model.match.MatchWeek;
import com.metawara.quickscore.utils.RandomSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MatchScheduleGenerator {

    private static final Logger logger = LoggerFactory.getLogger(MatchScheduleGenerator.class);

    Map<Integer, List<Integer>> matchups;

    Random rand = RandomSingleton.getInstance().getRnd();

    List<MatchWeek> generateMatchWeeks(List<FootballClub> leagueClubs) {
        List<MatchWeek> matchWeekHistory = new ArrayList<>();
        List<List<MonoPair<Integer>>> doubleRoundRobinMatchups = generateDoubleRoundRobinMatchups(leagueClubs.size());

        int internalWeekCounter = 1;
        List<FCMatch> matchesForCurrentWeek = new ArrayList<>();

        for(List<MonoPair<Integer>> listOfPairs : doubleRoundRobinMatchups){
            for(MonoPair<Integer> pair : listOfPairs){
                matchesForCurrentWeek.add(new FCMatch(leagueClubs.get(pair.getA()), leagueClubs.get(pair.getB())));
            }
            matchWeekHistory.add(new MatchWeek(internalWeekCounter, new ArrayList<>(matchesForCurrentWeek)));
            matchesForCurrentWeek = new ArrayList<>();
        }

        return matchWeekHistory;
    }

    private List<List<MonoPair<Integer>>> generateDoubleRoundRobinMatchups(int leagueSize) {
        int totalRounds = (leagueSize - 1) * 2;

        List<List<MonoPair<Integer>>> listOfMatchupsforMatchweeks = new ArrayList<>();

        for (int round = 0; round < totalRounds; round++) {
            List<MonoPair<Integer>> buffer = new ArrayList<>();
            for (int match = 0; match < leagueSize / 2; match++) {
                int home = (round + match) % (leagueSize - 1);
                int away = (leagueSize - 1 - match + round) % (leagueSize - 1);

                if (match == 0) {
                    away = leagueSize - 1;
                }

                if(totalRounds / 2 > totalRounds) {
                    buffer.add(MonoPair.of(away, home));
                }
                else {
                    buffer.add(MonoPair.of(home, away));
                }
            }
            listOfMatchupsforMatchweeks.add(buffer);
        }

        return listOfMatchupsforMatchweeks;
    }

}
