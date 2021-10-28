package com.metawara.quickscore.service;

import com.metawara.quickscore.model.FootballClub;
import com.metawara.quickscore.model.FootballMatchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = SimpleMatchLogicService.class)
@RunWith(MockitoJUnitRunner.class)
public class SimpleMatchLogicServiceTest {

    @Mock
    private FootballClub footballClub1;

    @Mock
    private FootballClub footballClub2;

    private MatchLogic matchLogic = new SimpleMatchLogicService();

    @Test
    public void simulateMatch_victory() {
        Mockito.when(footballClub1.getChanceOfWinning()).thenReturn(0.75);
        Mockito.when(footballClub2.getChanceOfWinning()).thenReturn(0.5);

        FootballMatchResult footballMatchResult = matchLogic.simulateMatch(footballClub1, footballClub2);
        assertTrue(footballMatchResult.getHomeSideMatchStatistics().getGoalsScored() > footballMatchResult.getAwaySideMatchStatistics().getGoalsScored());
    }

    @Test
    public void simulateMatch_draw() {
        Mockito.when(footballClub1.getChanceOfWinning()).thenReturn(0.5);
        Mockito.when(footballClub2.getChanceOfWinning()).thenReturn(0.5);

        FootballMatchResult footballMatchResult = matchLogic.simulateMatch(footballClub1, footballClub2);
        assertEquals(footballMatchResult.getHomeSideMatchStatistics().getGoalsScored(), footballMatchResult.getAwaySideMatchStatistics().getGoalsScored());

    }
}
