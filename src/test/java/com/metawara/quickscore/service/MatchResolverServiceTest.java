package com.metawara.quickscore.service;

import com.metawara.quickscore.model.FootballClub;
import com.metawara.quickscore.model.FootballMatchResult;
import com.metawara.quickscore.model.FootballClubMatchStatistics;
import com.metawara.quickscore.results.SimpleResultsDisplay;
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
    private FootballClub footballClub2;

    @Mock
    private FootballMatchResult footballMatchResult;

    @Mock
    private MatchLogic matchLogic;

    @Mock
    FootballClubMatchStatistics matchStatistics1;

    @Mock
    FootballClubMatchStatistics matchStatistics2;

    @Before
    public void mockBehavior() {
        mockFootballMatchResults();
        mockMatchLogic();
    }

    @Test
    public void verify_shouldFinishWithoutErrors() {
        MatchResolverService mc = new MatchResolverService(matchLogic, new SimpleResultsDisplay());
        FootballMatchResult matchResult = mc.simulateMatch(footballClub1, footballClub2);

        assertEquals(matchResult.getHomeSideMatchStatistics().getFootballClub(), footballClub1);
        assertEquals(matchResult.getAwaySideMatchStatistics().getFootballClub(), footballClub2);
    }

    /***** SETUP *****/

    private void mockFootballMatchResults() {
        Mockito.when(footballMatchResult.getHomeSideMatchStatistics()).thenReturn(matchStatistics1);
        Mockito.when(footballMatchResult.getAwaySideMatchStatistics()).thenReturn(matchStatistics2);
        Mockito.when(footballMatchResult.getHomeSideMatchStatistics().getFootballClub()).thenReturn(footballClub1);
        Mockito.when(footballMatchResult.getAwaySideMatchStatistics().getFootballClub()).thenReturn(footballClub2);
        Mockito.when(footballMatchResult.getHomeSideResult()).thenReturn(CLUB_1_NAME + " - 1");
        Mockito.when(footballMatchResult.getAwaySideResult()).thenReturn(CLUB_2_NAME + " - 2");
    }

    private void mockMatchLogic() {
        Mockito.when(matchLogic.simulateMatch(footballClub1, footballClub2)).thenReturn(footballMatchResult);
    }

}
