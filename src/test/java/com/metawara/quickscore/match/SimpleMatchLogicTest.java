package com.metawara.quickscore.match;

import com.metawara.quickscore.match.logic.MatchLogic;
import com.metawara.quickscore.match.logic.SimpleMatchLogic;
import com.metawara.quickscore.model.FootballClub;
import com.metawara.quickscore.model.match.FCMatchStatistics;
import com.metawara.quickscore.model.match.FCMatch;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = SimpleMatchLogic.class)
@RunWith(MockitoJUnitRunner.class)
public class SimpleMatchLogicTest {

    @Mock
    private FCMatch matchMock;

    @Mock
    private FCMatchStatistics fc1MatchStatistics;

    @Mock
    private FCMatchStatistics fc2MatchStatistics;

    @Mock
    private FootballClub footballClub1;

    @Mock
    private FootballClub footballClub2;

    private MatchLogic matchLogic = new SimpleMatchLogic();

    @Before
    public void initialize(){
        Mockito.when(matchMock.getHomeSideMatchStatistics()).thenReturn(fc1MatchStatistics);
        Mockito.when(matchMock.getAwaySideMatchStatistics()).thenReturn(fc2MatchStatistics);
    }

    @Test
    public void simulateMatch_shouldResultInVictoryForHomeSide() {
        Mockito.when(matchMock.getHomeSideWinningChance()).thenReturn(0.75);
        Mockito.when(matchMock.getAwaySideWinningChance()).thenReturn(0.5);

        Mockito.when(fc1MatchStatistics.getGoalsScored()).thenReturn(3);
        Mockito.when(fc2MatchStatistics.getGoalsScored()).thenReturn(2);

        FCMatch match = matchLogic.simulateMatch(matchMock);
        assertTrue(match.getHomeSideMatchStatistics().getGoalsScored() > match.getAwaySideMatchStatistics().getGoalsScored());
    }

    @Test
    public void simulateMatch_shouldResultInVictoryForAwaySide() {
        Mockito.when(matchMock.getHomeSideWinningChance()).thenReturn(0.5);
        Mockito.when(matchMock.getAwaySideWinningChance()).thenReturn(0.55);

        Mockito.when(fc1MatchStatistics.getGoalsScored()).thenReturn(2);
        Mockito.when(fc2MatchStatistics.getGoalsScored()).thenReturn(5);

        FCMatch match = matchLogic.simulateMatch(matchMock);
        assertTrue(match.getAwaySideMatchStatistics().getGoalsScored() > match.getHomeSideMatchStatistics().getGoalsScored());
    }

    @Test
    public void simulateMatch_shouldResultInDraw() {
        Mockito.when(matchMock.getHomeSideWinningChance()).thenReturn(0.5);
        Mockito.when(matchMock.getAwaySideWinningChance()).thenReturn(0.5);

        FCMatch match = matchLogic.simulateMatch(matchMock);
        assertEquals(match.getHomeSideMatchStatistics().getGoalsScored(), match.getAwaySideMatchStatistics().getGoalsScored());

    }
}
