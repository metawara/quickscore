package com.metawara.quickscore.match;

import com.metawara.quickscore.model.match.FCMatch;
import com.metawara.quickscore.model.match.event.MatchEvent;

public interface FCMatchEventProcessor {

    void register(FCMatch source, MatchEvent matchEvent, boolean isHomeSide);
}
