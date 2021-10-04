package com.metawara.quickscore.service;

import com.metawara.quickscore.model.FootballClub;
import com.metawara.quickscore.model.FootballMatchResult;

public interface MatchLogic {
    public FootballMatchResult simulateMatch(FootballClub footballClub1, FootballClub footballClub2);
}