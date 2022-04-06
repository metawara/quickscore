package com.metawara.quickscore.model.roster;

import java.util.Set;

public class MatchRoster {
    Set<Footballer> firstTeam;
    Set<Footballer> bench;

    public MatchRoster(Set<Footballer> firstTeam, Set<Footballer> bench) {
        this.firstTeam = firstTeam;
        this.bench = bench;
    }

    public Set<Footballer> getFirstTeam() {
        return firstTeam;
    }

    public Set<Footballer> getBench() {
        return bench;
    }

    public Footballer getPotentialGoalscorer(){
        return firstTeam.stream().findAny().orElse(new Footballer(0, "", "", ""));
    }
}