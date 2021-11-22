package com.metawara.quickscore.match.logic;

import com.metawara.quickscore.model.match.FCMatchStatistics;
import com.metawara.quickscore.model.match.FCMatch;
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

    @Override
    public FCMatch simulateMatch(FCMatch match) {

        int homeSideGoalsScored;
        int awaySideGoalsScored;

        double homeSideWinningChance = match.getHomeSideWinningChance();
        double awaySideWinningChance = match.getAwaySideWinningChance();

        logger.debug("{} // {}", homeSideWinningChance, awaySideWinningChance);

        if (homeSideWinningChance > awaySideWinningChance) {
            homeSideGoalsScored = new Random().nextInt(4) + 1;
            awaySideGoalsScored = homeSideGoalsScored - new Random().nextInt(homeSideGoalsScored);
            homeSideGoalsScored++;
        } else if (homeSideWinningChance < awaySideWinningChance) {
            awaySideGoalsScored = new Random().nextInt(4) + 1;
            homeSideGoalsScored = awaySideGoalsScored - new Random().nextInt(awaySideGoalsScored);
            awaySideGoalsScored++;
        } else {
            homeSideGoalsScored = new Random().nextInt(5);
            awaySideGoalsScored = homeSideGoalsScored;
        }

        increaseGoals(match.getHomeSideMatchStatistics(), homeSideGoalsScored);
        increaseGoals(match.getAwaySideMatchStatistics(), homeSideGoalsScored);

        logger.debug("{} // {}", homeSideGoalsScored, awaySideGoalsScored);

        return match;
    }

    private void increaseGoals(FCMatchStatistics matchStatistics, int goalsScored) {
        for (int i = 0; i < goalsScored; i++) {
            matchStatistics.increaseGoalsScored();
        }
    }
}