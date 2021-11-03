package com.metawara.quickscore.validator;

import com.metawara.quickscore.model.roster.MatchRoster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RosterValidator {

    private RosterValidator() {
    }

    private static final Logger logger = LoggerFactory.getLogger(RosterValidator.class);

    public static boolean validate(MatchRoster roster) {
        boolean isFirstTeamValid;
        boolean isBenchValid;

        if (roster.getFirstTeam() == null) {
            logger.warn("First team roster is NULL!!! Is it a legacy object?");
            isFirstTeamValid = true;
        } else {
            logger.info("First team roster size: {}", roster.getFirstTeam().size());
            isFirstTeamValid = roster.getFirstTeam().size() == 11;
        }
        if (roster.getBench() == null) {
            logger.warn("Bench is NULL!!! Is it a legacy object?");
            isBenchValid = true;
        } else {
            logger.info("Bench size: {}", roster.getBench().size());
            isBenchValid = roster.getBench().size() == 9;
        }

        return isFirstTeamValid && isBenchValid;
    }
}
