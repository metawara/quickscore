package com.metawara.quickscore.match.logic;

import com.metawara.quickscore.match.FCMatchEventProcessor;
import com.metawara.quickscore.model.FootballClub;
import com.metawara.quickscore.model.match.FCMatch;
import com.metawara.quickscore.model.match.event.MatchEvent;
import com.metawara.quickscore.model.match.event.MatchEventType;
import com.metawara.quickscore.utils.RandomSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class VarianceMatchLogic implements MatchLogic {

    private static final Logger logger = LoggerFactory.getLogger(VarianceMatchLogic.class);
    Random rand = RandomSingleton.getInstance().getRnd();

    FCMatchEventProcessor eventProcessor;

    FinalVarianceCalculator fvc;

    @Override
    public FCMatch simulateMatch(FCMatch match) {

        int homeSideGoalsScored;
        int awaySideGoalsScored;

        FootballClub homeSide = match.getHomeSideMatchStatistics().getFootballClub();
        FootballClub awaySide = match.getAwaySideMatchStatistics().getFootballClub();

        double homeSideWinningChance = FinalVarianceCalculator.calculate(
                homeSide.getBaseWinningChance(),
                homeSide.getPositiveWCVariance(),
                homeSide.getNegativeWCVariance());

        double awaySideWinningChance = FinalVarianceCalculator.calculate(
                awaySide.getBaseWinningChance(),
                awaySide.getPositiveWCVariance(),
                awaySide.getNegativeWCVariance());

        logger.debug("{} // {}", homeSideWinningChance, awaySideWinningChance);

        if (homeSideWinningChance > awaySideWinningChance) {
            homeSideGoalsScored = rand.nextInt(5) + 1;
            awaySideGoalsScored = homeSideGoalsScored - rand.nextInt(homeSideGoalsScored);
            homeSideGoalsScored++;
        } else if (homeSideWinningChance < awaySideWinningChance) {
            awaySideGoalsScored = rand.nextInt(5) + 1;
            homeSideGoalsScored = awaySideGoalsScored - rand.nextInt(awaySideGoalsScored);
            awaySideGoalsScored++;
        } else {
            homeSideGoalsScored = rand.nextInt(6);
            awaySideGoalsScored = homeSideGoalsScored;
        }

        for (int i = 0; i < homeSideGoalsScored; i++) {
            eventProcessor.register(match, new MatchEvent(MatchEventType.GOAL,
                            rand.nextInt(90) + 1,
                            match.getHomeSideMatchStatistics().getMatchRoster().getPotentialGoalscorer()),
                    true);
            match.getHomeSideMatchStatistics().increaseGoalsScored();
        }

        for (int i = 0; i < awaySideGoalsScored; i++) {
            eventProcessor.register(match, new MatchEvent(MatchEventType.GOAL,
                            rand.nextInt(90) + 1,
                            match.getAwaySideMatchStatistics().getMatchRoster().getPotentialGoalscorer()),
                    false);
            match.getAwaySideMatchStatistics().increaseGoalsScored();
        }

        return match;
    }
}
