package com.metawara.quickscore.competition;

import com.metawara.quickscore.model.FootballClub;
import com.metawara.quickscore.model.MonoPair;
import com.metawara.quickscore.model.match.FCMatch;
import com.metawara.quickscore.model.match.MatchWeek;

import java.util.ArrayList;
import java.util.List;

/**
 *  A schedule generator for various types of competitions.
 */
public class MatchScheduleGenerator {

    private MatchScheduleGenerator(){}

    public static List<MatchWeek> generateMatchWeeksForDoubleRoundRobinTrmt(List<FootballClub> leagueClubs) {
        List<List<MonoPair<Integer>>> roundRobinMatchups = TournamentSystemGenerator.generateRoundRobinMatchups(leagueClubs.size());

        List<List<MonoPair<Integer>>> invertedRoundRobinMatchups = new ArrayList<>();
        for(List<MonoPair<Integer>> matchWeekMatchup : roundRobinMatchups){
            List<MonoPair<Integer>> invertedMatchWeekMatchup = new ArrayList<>();

            matchWeekMatchup.forEach(pair -> invertedMatchWeekMatchup.add(pair.invert()));
            invertedRoundRobinMatchups.add(invertedMatchWeekMatchup);
        }

        roundRobinMatchups.addAll(invertedRoundRobinMatchups);

        int internalWeekCounter = 1;
        List<FCMatch> matchesForCurrentWeek = new ArrayList<>();

        List<MatchWeek> matchWeekHistory = new ArrayList<>();
        for(List<MonoPair<Integer>> listOfPairs : roundRobinMatchups){
            for(MonoPair<Integer> pair : listOfPairs){
                matchesForCurrentWeek.add(new FCMatch(leagueClubs.get(pair.getA()), leagueClubs.get(pair.getB())));
            }
            matchWeekHistory.add(new MatchWeek(internalWeekCounter++, new ArrayList<>(matchesForCurrentWeek)));
            matchesForCurrentWeek = new ArrayList<>();
        }

        return matchWeekHistory;
    }

}
