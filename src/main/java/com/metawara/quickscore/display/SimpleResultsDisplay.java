package com.metawara.quickscore.display;

import com.metawara.quickscore.model.match.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleResultsDisplay {

    private static final Logger logger = LoggerFactory.getLogger(SimpleResultsDisplay.class);

    public void display(Match match) {
        logger.info("{} - {}", match.getHomeSideResult(), match.getAwaySideResult());
    }
}
