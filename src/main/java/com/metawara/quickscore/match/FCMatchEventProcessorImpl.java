package com.metawara.quickscore.match;

import com.metawara.quickscore.model.match.FCMatch;
import com.metawara.quickscore.model.match.event.MatchEvent;

public class FCMatchEventProcessorImpl implements FCMatchEventProcessor{

    @Override
    public void register(FCMatch source, MatchEvent matchEvent, boolean isHomeSide) {
        if (isHomeSide) {
            source.getHomeSideMatchStatistics().addToMatchEventList(matchEvent);
        } else {
            source.getAwaySideMatchStatistics().addToMatchEventList(matchEvent);
        }
    }
}
