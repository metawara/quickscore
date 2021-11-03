package com.metawara.quickscore.validator;

import com.metawara.quickscore.model.roster.MatchRoster;

public class RosterValidator {

    private RosterValidator() {
    }

    public static boolean validate(MatchRoster roster) {
        return roster.getFirstTeam().size() == 11 && roster.getBench().size() == 9;
    }
}
