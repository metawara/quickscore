package com.metawara.quickscore.competition;

import com.metawara.quickscore.model.MonoPair;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides matchups for defined tournament types.
 */
public class TournamentSystemGenerator {

    private TournamentSystemGenerator(){}

    public static List<List<MonoPair<Integer>>> generateRoundRobinMatchups(int leagueSize) {
        int totalRounds = leagueSize - 1;

        List<List<MonoPair<Integer>>> matchups = new ArrayList<>();

        for (int round = 0; round < totalRounds; round++) {
            List<MonoPair<Integer>> buffer = new ArrayList<>();
            for (int match = 0; match < leagueSize / 2; match++) {
                int home = (round + match) % (leagueSize - 1);
                int away = (leagueSize - 1 - match + round) % (leagueSize - 1);

                if (match == 0) {
                    away = leagueSize - 1;
                }

                buffer.add(MonoPair.of(home, away));
            }
            matchups.add(buffer);
        }

        return matchups;
    }
}
