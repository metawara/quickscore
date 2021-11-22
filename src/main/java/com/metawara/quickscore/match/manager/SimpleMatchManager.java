package com.metawara.quickscore.match.manager;

import com.metawara.quickscore.display.SimpleResultsDisplay;
import com.metawara.quickscore.match.logic.SimpleMatchLogic;
import com.metawara.quickscore.model.match.Match;
import com.metawara.quickscore.validator.RosterValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * A simple match manager that runs validations, simulates and prints adequate information.
 */

public class SimpleMatchManager implements MatchManager {

    private static final Logger logger = LoggerFactory.getLogger(SimpleMatchManager.class);

    private final SimpleMatchLogic matchLogic;
    private final SimpleResultsDisplay resultsDisplay;

    public SimpleMatchManager(SimpleMatchLogic matchLogic, SimpleResultsDisplay resultsDisplay) {
        this.matchLogic = matchLogic;
        this.resultsDisplay = resultsDisplay;
    }

    /**
     * Manage a match.
     *
     * @param match unresolved, clean-slate match with filled rosters and such
     * @return simulated match with resolved scoreline
     */
    @Override
    public Match manageMatch(Match match) {
        if (RosterValidator.validate(match.getHomeSideMatchStatistics().getMatchRoster())
                && RosterValidator.validate(match.getAwaySideMatchStatistics().getMatchRoster())) {
            Match matchResult = matchLogic.simulateMatch(match);
            resultsDisplay.display(matchResult);
            return matchResult;
        } else {
            logger.error("Unable to validate rosters. Returning un-simulated match.");
            return match;
        }
    }
}
