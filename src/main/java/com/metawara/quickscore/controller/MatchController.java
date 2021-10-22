package com.metawara.quickscore.controller;

import com.metawara.quickscore.model.FootballClub;
import com.metawara.quickscore.model.FootballMatchResult;
import com.metawara.quickscore.results.ResultsDisplay;
import com.metawara.quickscore.service.MatchLogic;

public class MatchController {

    private final MatchLogic matchLogic;
    private final ResultsDisplay resultsDisplay;



    public MatchController(MatchLogic matchLogic, ResultsDisplay resultsDisplay) {
        this.matchLogic = matchLogic;
        this.resultsDisplay = resultsDisplay;
    }

    public FootballMatchResult simulateMatch(FootballClub footballClub1, FootballClub footballClub2) {
        FootballMatchResult matchResult = matchLogic.simulateMatch(footballClub1, footballClub2);
        resultsDisplay.display(matchResult);
        return matchResult;
    }
}
