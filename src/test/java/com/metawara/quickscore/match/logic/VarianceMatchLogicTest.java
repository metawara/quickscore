package com.metawara.quickscore.match.logic;

import com.metawara.quickscore.match.FCMatchEventProcessor;
import com.metawara.quickscore.model.FootballClub;
import com.metawara.quickscore.model.match.FCMatch;
import com.metawara.quickscore.model.match.FCMatchStatistics;
import com.metawara.quickscore.model.roster.Footballer;
import com.metawara.quickscore.model.roster.MatchRoster;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = VarianceMatchLogicTest.class)
@RunWith(MockitoJUnitRunner.class)
public class VarianceMatchLogicTest {

    @Mock
    FCMatchEventProcessor eventProcessor;

    @Mock
    FinalVarianceCalculator fvc;

    private FCMatch match;

    private FCMatchStatistics fc1MatchStatistics;

    private FCMatchStatistics fc2MatchStatistics;

    @Mock
    private FootballClub footballClub1;

    @Mock
    private FootballClub footballClub2;

    @Mock
    private Footballer footballer1;

    @Mock
    private Footballer footballer2;

    @InjectMocks
    private final MatchLogic matchLogic = new VarianceMatchLogic();

    @Before
    public void initialize(){

    }

    @Test
    public void simulateMatch_noVariance_shouldResultInVictoryForHomeSide() {
        Mockito.when(footballClub1.getBaseWinningChance()).thenReturn(0.5);
        Mockito.when(footballClub1.getPositiveWCVariance()).thenReturn(0.0);
        Mockito.when(footballClub1.getNegativeWCVariance()).thenReturn(0.0);

        Mockito.when(footballClub2.getBaseWinningChance()).thenReturn(0.4);
        Mockito.when(footballClub2.getPositiveWCVariance()).thenReturn(0.0);
        Mockito.when(footballClub2.getNegativeWCVariance()).thenReturn(0.0);

        fc1MatchStatistics = new FCMatchStatistics(footballClub1, new MatchRoster(Set.of(footballer1), new HashSet<>()), 0, new ArrayList<>());
        fc2MatchStatistics = new FCMatchStatistics(footballClub2, new MatchRoster(Set.of(footballer2), new HashSet<>()), 0, new ArrayList<>());

        match = new FCMatch(fc1MatchStatistics, fc2MatchStatistics);
        matchLogic.simulateMatch(match);
        assertTrue(match.getHomeSideMatchStatistics().getGoalsScored() > match.getAwaySideMatchStatistics().getGoalsScored());
    }

    @Test
    public void simulateMatch_noVariance_shouldResultInVictoryForAwaySide() {
        Mockito.when(footballClub1.getBaseWinningChance()).thenReturn(0.5);
        Mockito.when(footballClub1.getPositiveWCVariance()).thenReturn(0.0);
        Mockito.when(footballClub1.getNegativeWCVariance()).thenReturn(0.0);

        Mockito.when(footballClub2.getBaseWinningChance()).thenReturn(0.55);
        Mockito.when(footballClub2.getPositiveWCVariance()).thenReturn(0.0);
        Mockito.when(footballClub2.getNegativeWCVariance()).thenReturn(0.0);

        fc1MatchStatistics = new FCMatchStatistics(footballClub1, new MatchRoster(Set.of(footballer1), new HashSet<>()), 0, new ArrayList<>());
        fc2MatchStatistics = new FCMatchStatistics(footballClub2, new MatchRoster(Set.of(footballer2), new HashSet<>()), 0, new ArrayList<>());

        match = new FCMatch(fc1MatchStatistics, fc2MatchStatistics);
        matchLogic.simulateMatch(match);
        assertTrue(match.getHomeSideMatchStatistics().getGoalsScored() < match.getAwaySideMatchStatistics().getGoalsScored());
    }


}
