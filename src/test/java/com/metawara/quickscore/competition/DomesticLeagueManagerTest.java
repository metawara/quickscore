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

    List<FootballClub> clubs;

    @Before
    public void init(){
        clubs = prepareMockedClubs();
        Mockito.when(importer.importClubs()).thenReturn(clubs);
    }

    @Test
    public void simulateASeason_shouldProperlyGenerateFixtures(){
        domesticLeagueManager = new DomesticLeagueManager(matchManager, importer);
        simulateWeeks();

        assertEquals(clubs.size() * 2L - 2, domesticLeagueManager.getWeekCounter());
        assertEquals((long) clubs.size() * clubs.size() - clubs.size(), domesticLeagueManager.getMatchWeekHistory()
                .values().stream().mapToLong(List::size).sum());

    }

    private void simulateWeeks() {
        int weekCounter = clubs.size() * 2 - 2;
        int i = 0;
        while( i < weekCounter){
            domesticLeagueManager.simulateWeek();
            i++;
        }
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

        FootballClub fc5 = Mockito.mock(FootballClub.class);
        Mockito.when(fc5.getName()).thenReturn("Football Club 5");

        FootballClub fc6 = Mockito.mock(FootballClub.class);
        Mockito.when(fc6.getName()).thenReturn("Football Club 6");

        FootballClub fc7 = Mockito.mock(FootballClub.class);
        Mockito.when(fc7.getName()).thenReturn("Football Club 7");

        FootballClub fc8 = Mockito.mock(FootballClub.class);
        Mockito.when(fc8.getName()).thenReturn("Football Club 8");

        FootballClub fc9 = Mockito.mock(FootballClub.class);
        Mockito.when(fc9.getName()).thenReturn("Football Club 9");

        FootballClub fc10 = Mockito.mock(FootballClub.class);
        Mockito.when(fc10.getName()).thenReturn("Football Club 10");

        return List.of(fc1, fc2, fc3, fc4, fc5, fc6, fc7, fc8, fc9, fc10);
    }
}
