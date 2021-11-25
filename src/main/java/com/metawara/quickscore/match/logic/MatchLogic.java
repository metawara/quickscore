package com.metawara.quickscore.match.logic;

import com.metawara.quickscore.model.match.FCMatch;

/**
 * MatchLogic-implementing classes, for a given, clean-slate, prepared Match object,
 * need to simulate an entire match (scored goals, match events, etc)
 */

public interface MatchLogic {
    FCMatch simulateMatch(FCMatch match);
}