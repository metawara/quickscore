package com.metawara.quickscore.controller;

import com.metawara.quickscore.model.FootballClub;
import com.metawara.quickscore.model.FootballMatchResult;
import com.metawara.quickscore.service.MatchLogic;
import com.metawara.quickscore.service.MatchLogicService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MatchControllerTest {

    private static final String CLUB_1_NAME = "Club Name 1";

    private FootballClub footballClub1;
    private FootballClub footballClub2;

    private FootballMatchResult footballMatchResult;

    private MatchLogic matchLogic;

    @Before
    public void mockBehavior() {
        mockFootballClubs();
        mockFootballMatchResults();
        mockMatchLogic();
    }

    @Test
    public void simulateMatch_shouldPass() {
        MatchController mc = new MatchController(matchLogic);
        FootballMatchResult matchResult = mc.simulateMatch(footballClub1, footballClub2);

        assertEquals(matchResult.getWinner(), footballClub1);
        assertEquals(matchResult.getLoser(), footballClub2);
    }

    private void mockFootballClubs() {
        footballClub1 = Mockito.mock(FootballClub.class);
        footballClub2 = Mockito.mock(FootballClub.class);

        Mockito.when(footballClub1.getName()).thenReturn(CLUB_1_NAME);
    }

    private void mockFootballMatchResults() {
        footballMatchResult = Mockito.mock(FootballMatchResult.class);
        Mockito.when(footballMatchResult.getWinner()).thenReturn(footballClub1);
        Mockito.when(footballMatchResult.getLoser()).thenReturn(footballClub2);
    }

    private void mockMatchLogic() {
        matchLogic = Mockito.mock(MatchLogicService.class);
        Mockito.when(matchLogic.simulateMatch(footballClub1, footballClub2)).thenReturn(footballMatchResult);
    }

}
