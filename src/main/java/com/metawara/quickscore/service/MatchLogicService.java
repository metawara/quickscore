package com.metawara.quickscore.service;

import com.metawara.quickscore.model.FootballClub;
import com.metawara.quickscore.model.FootballMatchResult;
import org.springframework.stereotype.Service;

@Service
public class MatchLogicService implements MatchLogic{

    @Override
    public FootballMatchResult simulateMatch(FootballClub footballClub1, FootballClub footballClub2) {
        if(footballClub1.getChanceOfWinnning() > footballClub2.getChanceOfWinnning()){
            return new FootballMatchResult(footballClub1, footballClub2);
        }
        else {
            return new FootballMatchResult(footballClub2, footballClub1);
        }
    }
}