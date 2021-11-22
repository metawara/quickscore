package com.metawara.quickscore.match.logic;

import com.metawara.quickscore.model.match.Match;

/**
 * MatchLogic-implementing classes, for a given, clean-slate, prepared Match object,
 * need to simulate an entire match (scored goals, match events, etc)
 */

public interface MatchLogic {
    Match simulateMatch(Match match);
}