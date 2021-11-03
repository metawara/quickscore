package com.metawara.quickscore.service;

import com.metawara.quickscore.model.FootballClub;
import com.metawara.quickscore.model.FootballClubMatchStatistics;
import com.metawara.quickscore.model.FootballMatchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SimpleMatchLogicService implements MatchLogic {

    private static final Logger logger = LoggerFactory.getLogger(SimpleMatchLogicService.class);

    @Override
    public FootballMatchResult simulateMatch(FootballClub homeSide, FootballClub awaySide) {
        FootballClubMatchStatistics homeSideStatistics = new FootballClubMatchStatistics(homeSide, 0);
        FootballClubMatchStatistics awaySideStatistics = new FootballClubMatchStatistics(awaySide, 0);

        int homeSideGoalsScored;
        int awaySideGoalsScored;

        logger.debug("{} // {}", homeSide.getChanceOfWinning(), awaySide.getChanceOfWinning());

        if (homeSide.getChanceOfWinning() > awaySide.getChanceOfWinning()) {
            homeSideGoalsScored = new Random().nextInt(5);
            awaySideGoalsScored = homeSideGoalsScored - new Random().nextInt(homeSideGoalsScored);
            homeSideGoalsScored++;
        } else if (homeSide.getChanceOfWinning() < awaySide.getChanceOfWinning()) {
            awaySideGoalsScored = new Random().nextInt(5);
            homeSideGoalsScored = awaySideGoalsScored - new Random().nextInt(awaySideGoalsScored);
            awaySideGoalsScored++;
        } else {
            homeSideGoalsScored = new Random().nextInt(5);
            awaySideGoalsScored = homeSideGoalsScored;
        }

        increaseGoals(homeSideStatistics, awaySideStatistics, homeSideGoalsScored, awaySideGoalsScored);

        logger.debug("{} // {}", homeSideGoalsScored, awaySideGoalsScored);

        return new FootballMatchResult(homeSideStatistics, awaySideStatistics);
    }



    private void increaseGoals(FootballClubMatchStatistics homeSideStatistics, FootballClubMatchStatistics awaySideStatistics, int homeSideGoalsScored, int awaySideGoalsScored) {
        for (int i = 0; i < homeSideGoalsScored; i++) {
            homeSideStatistics.increaseGoalsScored();
        }

        for (int i = 0; i < awaySideGoalsScored; i++) {
            awaySideStatistics.increaseGoalsScored();
        }
    }
}