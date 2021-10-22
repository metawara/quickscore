package com.metawara.quickscore.results;

import com.metawara.quickscore.model.FootballMatchResult;

import java.util.logging.Logger;

/**
 * A simple results display utilizing the standard sout printing methods.
 */
public class SimpleResultsDisplay implements ResultsDisplay {

    private final Logger logger = Logger.getLogger(SimpleResultsDisplay.class.toString());

    @Override
    public void display(FootballMatchResult footballMatchResult) {
        logger.info(footballMatchResult.getHomeSideMatchStatistics().getFootballClub().getName() + ": "
                + footballMatchResult.getHomeSideMatchStatistics().getGoalsScored());

        logger.info(footballMatchResult.getAwaySideMatchStatistics().getFootballClub().getName() + ": "
                + footballMatchResult.getAwaySideMatchStatistics().getGoalsScored());
    }
}
