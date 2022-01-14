package com.metawara.quickscore.match.logic;

import com.metawara.quickscore.model.match.FCMatch;
import com.metawara.quickscore.model.match.FCMatchStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;


/**
 * Simple match logic that only generates score lines (without generating any events, such as
 * scored goals assigned to a footballer, injuries, substitutions and such).
 * Depends solely on winning chance and generates a result based on them.
 */
public class SimpleMatchLogic implements MatchLogic {

    private static final Logger logger = LoggerFactory.getLogger(SimpleMatchLogic.class);
    Random rand = new Random();

    @Override
    public FCMatch simulateMatch(FCMatch match) {

        int homeSideGoalsScored;
        int awaySideGoalsScored;

        double homeSideWinningChance = match.getHomeSideWinningChance();
        double awaySideWinningChance = match.getAwaySideWinningChance();

        logger.debug("{} // {}", homeSideWinningChance, awaySideWinningChance);

        if (homeSideWinningChance > awaySideWinningChance) {
            homeSideGoalsScored = rand.nextInt(4) + 1;
            awaySideGoalsScored = homeSideGoalsScored - rand.nextInt(homeSideGoalsScored);
            homeSideGoalsScored++;
        } else if (homeSideWinningChance < awaySideWinningChance) {
            awaySideGoalsScored = rand.nextInt(4) + 1;
            homeSideGoalsScored = awaySideGoalsScored - rand.nextInt(awaySideGoalsScored);
            awaySideGoalsScored++;
        } else {
            homeSideGoalsScored = rand.nextInt(5);
            awaySideGoalsScored = homeSideGoalsScored;
        }

        increaseGoals(match.getHomeSideMatchStatistics(), homeSideGoalsScored);
        increaseGoals(match.getAwaySideMatchStatistics(), awaySideGoalsScored);

        logger.debug("{} // {}", homeSideGoalsScored, awaySideGoalsScored);

        return match;
    }

    private void increaseGoals(FCMatchStatistics matchStatistics, int goalsScored) {
        for (int i = 0; i < goalsScored; i++) {
            matchStatistics.increaseGoalsScored();
        }
    }
}