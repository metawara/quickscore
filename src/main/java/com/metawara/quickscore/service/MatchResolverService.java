package com.metawara.quickscore.service;

import com.metawara.quickscore.display.SimpleResultsDisplay;
import com.metawara.quickscore.model.match.Match;
import com.metawara.quickscore.validator.RosterValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MatchResolverService {

    private static final Logger logger = LoggerFactory.getLogger(MatchResolverService.class);

    private final MatchLogic matchLogic;
    private final SimpleResultsDisplay resultsDisplay;

    public MatchResolverService(MatchLogic matchLogic, SimpleResultsDisplay resultsDisplay) {
        this.matchLogic = matchLogic;
        this.resultsDisplay = resultsDisplay;
    }

    public Match resolve(Match match) {
        if (RosterValidator.validate(match.getHomeSideMatchStatistics().getMatchRoster())
                && RosterValidator.validate(match.getAwaySideMatchStatistics().getMatchRoster())) {
            Match matchResult = matchLogic.simulateMatch(match);
            resultsDisplay.display(matchResult);
            return matchResult;
        } else {
            logger.error("Unable to validate rosters. Returning unresolved match.");
            return match;
        }
    }
}
