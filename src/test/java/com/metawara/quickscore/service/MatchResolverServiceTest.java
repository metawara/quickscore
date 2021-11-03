package com.metawara.quickscore.service;

import com.metawara.quickscore.model.FootballClub;
import com.metawara.quickscore.model.match.Match;
import com.metawara.quickscore.model.match.FCMatchStatistics;
import com.metawara.quickscore.display.SimpleResultsDisplay;
import com.metawara.quickscore.model.roster.MatchRoster;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MatchResolverServiceTest {

    private static final String CLUB_1_NAME = "Club Name 1";
    private static final String CLUB_2_NAME = "Club Name 2";

    private FootballClub footballClub1;

    @Mock
    private FootballClub footballClub2;

    @Mock
    private Match match;

    @Mock
    private MatchRoster roster;

    @Mock
    private MatchLogic matchLogic;

    @Mock
    FCMatchStatistics FCMatchStatistics1;

    @Mock
    FCMatchStatistics FCMatchStatistics2;

    @Before
    public void mockBehavior() {
        mockFootballMatchResults();
        mockMatchLogic();
    }

    @Test
    public void verify_shouldFinishWithoutErrors() {
        MatchResolverService mc = new MatchResolverService(matchLogic, new SimpleResultsDisplay());
        Match matchResult = mc.resolve(match);

        assertEquals(matchResult.getHomeSideMatchStatistics().getFootballClub(), footballClub1);
        assertEquals(matchResult.getAwaySideMatchStatistics().getFootballClub(), footballClub2);
    }

    /***** SETUP *****/

    private void mockFootballMatchResults() {
        Mockito.when(match.getHomeSideMatchStatistics()).thenReturn(FCMatchStatistics1);
        Mockito.when(match.getAwaySideMatchStatistics()).thenReturn(FCMatchStatistics2);
        Mockito.when(match.getHomeSideMatchStatistics().getFootballClub()).thenReturn(footballClub1);
        Mockito.when(match.getAwaySideMatchStatistics().getFootballClub()).thenReturn(footballClub2);
        Mockito.when(match.getHomeSideResult()).thenReturn(CLUB_1_NAME + " - 1");
        Mockito.when(match.getAwaySideResult()).thenReturn(CLUB_2_NAME + " - 2");
    }

    private void mockMatchLogic() {
        Mockito.when(matchLogic.simulateMatch(match)).thenReturn(match);
    }
}
