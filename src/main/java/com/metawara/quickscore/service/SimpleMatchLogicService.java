package com.metawara.quickscore.service;

import com.metawara.quickscore.model.match.FCMatchStatistics;
import com.metawara.quickscore.model.match.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SimpleMatchLogicService implements MatchLogic {

    private static final Logger logger = LoggerFactory.getLogger(SimpleMatchLogicService.class);

    @Override
    public Match simulateMatch(Match match) {

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