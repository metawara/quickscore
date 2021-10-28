package com.metawara.quickscore.results;

import com.metawara.quickscore.model.FootballMatchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleResultsDisplay implements ResultsDisplay {

    private static final Logger logger = LoggerFactory.getLogger(SimpleResultsDisplay.class);

    @Override
    public void display(FootballMatchResult footballMatchResult) {
        logger.info("{} - {}", footballMatchResult.getHomeSideResult(), footballMatchResult.getAwaySideResult());
    }
}
