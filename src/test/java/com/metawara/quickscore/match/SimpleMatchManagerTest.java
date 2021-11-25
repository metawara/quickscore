package com.metawara.quickscore.match;

import com.metawara.quickscore.match.logic.SimpleMatchLogic;
import com.metawara.quickscore.match.manager.SimpleMatchManager;
import com.metawara.quickscore.model.FootballClub;
import com.metawara.quickscore.model.match.FCMatch;
import com.metawara.quickscore.model.match.FCMatchStatistics;
import com.metawara.quickscore.display.SimpleResultsPrinter;
import com.metawara.quickscore.model.roster.MatchRoster;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class SimpleMatchManagerTest {

    private static final String CLUB_1_NAME = "Club Name 1";
    private static final String CLUB_2_NAME = "Club Name 2";

    private FootballClub footballClub1;

    @Mock
    private FootballClub footballClub2;

    @Mock
    private FCMatch match;

    @Mock
    private MatchRoster roster;

    @Mock
    private SimpleMatchLogic matchLogic;

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
        SimpleMatchManager mc = new SimpleMatchManager(matchLogic, new SimpleResultsPrinter());
        FCMatch matchResult = mc.manageMatch(match);

        assertEquals(matchResult.getHomeSideMatchStatistics().getFootballClub(), footballClub1);
        assertEquals(matchResult.getAwaySideMatchStatistics().getFootballClub(), footballClub2);
    }

    /***** SETUP *****/

    private void mockFootballMatchResults() {
        Mockito.when(match.getHomeSideMatchStatistics()).thenReturn(FCMatchStatistics1);
        Mockito.when(match.getAwaySideMatchStatistics()).thenReturn(FCMatchStatistics2);
        Mockito.when(match.getHomeSideMatchStatistics().getFootballClub()).thenReturn(footballClub1);
        Mockito.when(match.getAwaySideMatchStatistics().getFootballClub()).thenReturn(footballClub2);
    }

    private void mockMatchLogic() {
        Mockito.when(matchLogic.simulateMatch(match)).thenReturn(match);
    }
}
