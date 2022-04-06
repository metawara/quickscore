package com.metawara.quickscore.model.match.event;

import com.metawara.quickscore.model.roster.Footballer;

public class MatchEvent {
    private final MatchEventType matchEventType;
    private final int timestamp;
    private final Footballer target;

    public MatchEvent(MatchEventType matchEventType, int timestamp, Footballer target) {
        this.matchEventType = matchEventType;
        this.timestamp = timestamp;
        this.target = target;
    }

    public MatchEventType getMatchEventType() {
        return matchEventType;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public Footballer getTarget() {
        return target;
    }
}
