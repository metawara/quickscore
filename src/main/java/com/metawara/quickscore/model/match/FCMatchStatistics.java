package com.metawara.quickscore.model.match;

import com.metawara.quickscore.model.FootballClub;
import com.metawara.quickscore.model.match.event.MatchEvent;
import com.metawara.quickscore.model.roster.MatchRoster;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class FCMatchStatistics {
    private final FootballClub footballClub;
    private final MatchRoster matchRoster;
    private int goalsScored;
    private final List<MatchEvent> matchEventList;

    public FCMatchStatistics(FootballClub footballClub, MatchRoster matchRoster, int goalsScored, List<MatchEvent> matchEventList) {
        this.matchRoster = matchRoster;
        this.footballClub = footballClub;
        this.goalsScored = goalsScored;
        this.matchEventList = matchEventList;
    }

    public FCMatchStatistics(FootballClub footballClub, int goalsScored) {
        this(footballClub, new MatchRoster(new HashSet<>(), new HashSet<>()), goalsScored, new ArrayList<>());
    }

    public void addToMatchEventList(MatchEvent matchEvent) {
        matchEventList.add(matchEvent);
    }

    public FootballClub getFootballClub() {
        return footballClub;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void increaseGoalsScored() {
        goalsScored++;
    }

    public MatchRoster getMatchRoster() {
        return matchRoster;
    }
}
