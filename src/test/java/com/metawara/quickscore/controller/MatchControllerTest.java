package com.metawara.quickscore.controller;

import com.metawara.quickscore.model.FootballClub;
import com.metawara.quickscore.model.FootballMatchResult;
import com.metawara.quickscore.model.MatchStatistics;
import com.metawara.quickscore.results.ResultsDisplay;
import com.metawara.quickscore.results.SimpleResultsDisplay;
import com.metawara.quickscore.service.MatchLogic;
import com.metawara.quickscore.service.SimpleMatchLogicService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MatchControllerTest {

    private static final String CLUB_1_NAME = "Club Name 1";
    private static final String CLUB_2_NAME = "Club Name 2";

    private FootballClub footballClub1;
    private FootballClub footballClub2;

    private FootballMatchResult footballMatchResult;

    private MatchLogic matchLogic;

    @Before
    public void mockBehavior() {
        mockFootballMatchResults();
        mockMatchLogic();
    }

    @Test
    public void simulateMatch_shouldPass() {
        MatchController mc = new MatchController(matchLogic, new SimpleResultsDisplay());
        FootballMatchResult matchResult = mc.simulateMatch(footballClub1, footballClub2);

        assertEquals(matchResult.getHomeSideMatchStatistics().getFootballClub(), footballClub1);
        assertEquals(matchResult.getAwaySideMatchStatistics().getFootballClub(), footballClub2);
    }

    private void mockFootballMatchResults() {
        footballMatchResult = Mockito.mock(FootballMatchResult.class);

        MatchStatistics matchStatistics1 = Mockito.mock(MatchStatistics.class);
        MatchStatistics matchStatistics2 = Mockito.mock(MatchStatistics.class);

        Mockito.when(footballMatchResult.getHomeSideMatchStatistics()).thenReturn(matchStatistics1);
        Mockito.when(footballMatchResult.getAwaySideMatchStatistics()).thenReturn(matchStatistics2);
        Mockito.when(footballMatchResult.getHomeSideMatchStatistics().getFootballClub()).thenReturn(footballClub1);
        Mockito.when(footballMatchResult.getAwaySideMatchStatistics().getFootballClub()).thenReturn(footballClub2);
        Mockito.when(footballMatchResult.getHomeSideResult()).thenReturn(CLUB_1_NAME + " - 1");
        Mockito.when(footballMatchResult.getAwaySideResult()).thenReturn(CLUB_2_NAME + " - 2");
    }

    private void mockMatchLogic() {
        matchLogic = Mockito.mock(SimpleMatchLogicService.class);
        Mockito.when(matchLogic.simulateMatch(footballClub1, footballClub2)).thenReturn(footballMatchResult);
    }

}
