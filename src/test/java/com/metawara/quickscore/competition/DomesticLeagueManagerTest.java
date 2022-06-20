package com.metawara.quickscore.competition;

import com.metawara.quickscore.importer.FootballClubImporter;
import com.metawara.quickscore.match.manager.MatchManager;
import com.metawara.quickscore.model.FootballClub;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = DomesticLeagueManagerTest.class)
@RunWith(MockitoJUnitRunner.class)
public class DomesticLeagueManagerTest {

    @Mock
    MatchScheduleGenerator matchScheduleGenerator;

    @Mock
    MatchManager matchManager;

    @Mock
    FootballClubImporter importer;

    DomesticLeagueManager domesticLeagueManager;

    @Before
    public void init() {
        FootballClub fc = Mockito.mock(FootballClub.class);
        Mockito.when(fc.getName()).thenReturn("FC 1");

        FootballClub fc2 = Mockito.mock(FootballClub.class);
        Mockito.when(fc2.getName()).thenReturn("FC 2");

        FootballClub fc3 = Mockito.mock(FootballClub.class);
        Mockito.when(fc3.getName()).thenReturn("FC 3");

        FootballClub fc4 = Mockito.mock(FootballClub.class);
        Mockito.when(fc4.getName()).thenReturn("FC 4");

        Mockito.when(importer.importClubs()).thenReturn(List.of(fc, fc2, fc3, fc4));
    }

    @Test
    public void simulateASeason_shouldProperlyIncreaseWeekCounter() {
        domesticLeagueManager = new DomesticLeagueManager(matchManager);
        domesticLeagueManager.initialize(importer);
        domesticLeagueManager.simulateWeek();

        assertEquals(1, domesticLeagueManager.getWeekCounter());

        for(int a = 0; a < 8; a++){
            domesticLeagueManager.simulateWeek();
        }

        assertEquals(6, domesticLeagueManager.getWeekCounter());
    }

}
