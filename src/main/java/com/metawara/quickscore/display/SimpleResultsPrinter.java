package com.metawara.quickscore.display;

import com.metawara.quickscore.model.match.FCMatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleResultsPrinter {

    private static final Logger logger = LoggerFactory.getLogger(SimpleResultsPrinter.class);

    public void print(FCMatch match) {
        logger.info("{} - {}", match.getHomeSideResult(), match.getAwaySideResult());
    }
}
