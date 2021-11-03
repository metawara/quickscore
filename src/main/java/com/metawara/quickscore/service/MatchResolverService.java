package com.metawara.quickscore.service;

import com.metawara.quickscore.display.SimpleResultsDisplay;
import com.metawara.quickscore.model.match.Match;

public class MatchResolverService {

    private final MatchLogic matchLogic;
    private final SimpleResultsDisplay resultsDisplay;

    public MatchResolverService(MatchLogic matchLogic, SimpleResultsDisplay resultsDisplay) {
        this.matchLogic = matchLogic;
        this.resultsDisplay = resultsDisplay;
    }

    public Match resolve(Match match) {
        Match matchResult = matchLogic.simulateMatch(match);
        resultsDisplay.display(matchResult);
        return matchResult;
    }
}
