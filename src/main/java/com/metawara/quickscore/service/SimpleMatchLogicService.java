package com.metawara.quickscore.service;

import com.metawara.quickscore.model.FootballClub;
import com.metawara.quickscore.model.FootballMatchResult;
import com.metawara.quickscore.model.MatchStatistics;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SimpleMatchLogicService implements MatchLogic {

    @Override
    public FootballMatchResult simulateMatch(FootballClub homeSide, FootballClub awaySide) {
        int homeSideGoalsScored;
        int awaySideGoalsSCored;
        if (homeSide.getChanceOfWinning() > awaySide.getChanceOfWinning()) {
            homeSideGoalsScored = new Random().nextInt(5);
            awaySideGoalsSCored = homeSideGoalsScored - new Random().nextInt(5 - homeSideGoalsScored);
        }
        else if (homeSide.getChanceOfWinning() < awaySide.getChanceOfWinning()) {
            awaySideGoalsSCored = new Random().nextInt(5);
            homeSideGoalsScored = awaySideGoalsSCored - new Random().nextInt(5 - awaySideGoalsSCored);
        }
        else {
            homeSideGoalsScored = new Random().nextInt(5);
            awaySideGoalsSCored = homeSideGoalsScored;
        }

        MatchStatistics homeSideMatchStatistics = new MatchStatistics(homeSide, homeSideGoalsScored);
        MatchStatistics awaySideMatchStatistics = new MatchStatistics(homeSide, awaySideGoalsSCored);

        return new FootballMatchResult(homeSideMatchStatistics, awaySideMatchStatistics);
    }
}