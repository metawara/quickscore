package com.metawara.quickscore.service;

import com.metawara.quickscore.model.FootballClub;
import com.metawara.quickscore.model.FootballMatchResult;
import com.metawara.quickscore.model.MatchStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SimpleMatchLogicService implements MatchLogic {

    private static final Logger logger = LoggerFactory.getLogger(SimpleMatchLogicService.class);

    @Override
    public FootballMatchResult simulateMatch(FootballClub homeSide, FootballClub awaySide) {
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

        logger.debug("{} // {}", homeSideGoalsScored, awaySideGoalsScored);

        MatchStatistics homeSideMatchStatistics = new MatchStatistics(homeSide, homeSideGoalsScored);
        MatchStatistics awaySideMatchStatistics = new MatchStatistics(awaySide, awaySideGoalsScored);

        return new FootballMatchResult(homeSideMatchStatistics, awaySideMatchStatistics);
    }
}