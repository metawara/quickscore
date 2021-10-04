package com.metawara.quickscore.controller;

import com.metawara.quickscore.model.FootballClub;
import com.metawara.quickscore.model.FootballMatchResult;
import com.metawara.quickscore.service.MatchLogic;

public class MatchController {

    private MatchLogic matchLogic;

    public MatchController(MatchLogic matchLogic) {
        this.matchLogic = matchLogic;
    }

    public FootballMatchResult simulateMatch(FootballClub footballClub1, FootballClub footballClub2) {
        FootballMatchResult matchResult = matchLogic.simulateMatch(footballClub1, footballClub2);
        System.out.println("Winning team: " + matchResult.getWinner().getName());
        return matchResult;
    }
}
