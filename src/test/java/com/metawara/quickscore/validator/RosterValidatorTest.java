package com.metawara.quickscore.validator;

import com.metawara.quickscore.model.roster.Footballer;
import com.metawara.quickscore.model.roster.MatchRoster;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class RosterValidatorTest {

    @Mock
    private MatchRoster roster;

    @Mock
    private Set<Footballer> startingEleven;

    @Mock
    private Set<Footballer> bench;

    @Before
    public void init() {
        Mockito.when(roster.getFirstTeam()).thenReturn(startingEleven);
        Mockito.when(roster.getBench()).thenReturn(bench);
    }

    @Test
    public void testValidation_correctFirstTeamAndBenchSizes_shouldPass() {
        Mockito.when(startingEleven.size()).thenReturn(11);
        Mockito.when(bench.size()).thenReturn(9);

        assertTrue(RosterValidator.validate(roster));
    }

    @Test
    public void testValidation_variousInvalidSizes_shouldFail() {
        Mockito.when(startingEleven.size()).thenReturn(9);
        Mockito.when(bench.size()).thenReturn(9);

        assertFalse(RosterValidator.validate(roster));

        Mockito.when(startingEleven.size()).thenReturn(11);
        Mockito.when(bench.size()).thenReturn(7);

        assertFalse(RosterValidator.validate(roster));

        Mockito.when(startingEleven.size()).thenReturn(9);

        assertFalse(RosterValidator.validate(roster));
    }
}
