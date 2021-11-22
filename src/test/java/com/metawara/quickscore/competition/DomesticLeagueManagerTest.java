package com.metawara.quickscore.competition;

import com.metawara.quickscore.importer.JSONFootballClubImporter;
import com.metawara.quickscore.match.manager.MatchManager;
import com.metawara.quickscore.model.FootballClub;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = DomesticLeagueManager.class)
@RunWith(MockitoJUnitRunner.class)
public class DomesticLeagueManagerTest {

    @InjectMocks
    DomesticLeagueManager domesticLeagueManager;

    @Mock
    JSONFootballClubImporter importer;

    @Mock
    MatchManager matchManager;

    @Before
    public void init(){
        List<FootballClub> clubs = prepareMockedClubs();
        Mockito.when(importer.importClubs()).thenReturn(clubs);
    }

    @Test
    public void simulateAMatchWeek_shouldGenerateCorrectPairs() {
        domesticLeagueManager = new DomesticLeagueManager(matchManager, importer);
        domesticLeagueManager.simulateWeek();

        assertEquals(2, domesticLeagueManager.getWeekCounter());
    }

    private List<FootballClub> prepareMockedClubs() {
        FootballClub fc1 = Mockito.mock(FootballClub.class);
        Mockito.when(fc1.getName()).thenReturn("Football Club 1");

        FootballClub fc2 = Mockito.mock(FootballClub.class);
        Mockito.when(fc2.getName()).thenReturn("Football Club 2");

        FootballClub fc3 = Mockito.mock(FootballClub.class);
        Mockito.when(fc3.getName()).thenReturn("Football Club 3");

        FootballClub fc4 = Mockito.mock(FootballClub.class);
        Mockito.when(fc4.getName()).thenReturn("Football Club 4");

        return List.of(fc1, fc2, fc3, fc4);
    }
}
