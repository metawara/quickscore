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
    public void init() {
        clubs = prepareMockedClubs();
        Mockito.when(importer.importClubs()).thenReturn(clubs);
    }

    @Test
    public void simulateASeason_shouldProperlyGenerateFixtures() {
        domesticLeagueManager = new DomesticLeagueManager(matchManager, importer);
        simulateWeeks();

        assertEquals(clubs.size() * 2L - 2, domesticLeagueManager.getWeekCounter());
        assertEquals((long) clubs.size() * clubs.size() - clubs.size(), domesticLeagueManager.getMatchWeekHistory()
                .values().stream().mapToLong(List::size).sum());

    }

    private void simulateWeeks() {
        int weekCounter = clubs.size() * 2 - 2;
        int i = 0;
        while (i < weekCounter) {
            domesticLeagueManager.simulateWeek();
            i++;
        }
    }

    private List<FootballClub> prepareMockedClubs() {
        FootballClub fc = Mockito.mock(FootballClub.class);

        return List.of(fc, fc, fc, fc, fc, fc, fc, fc, fc, fc,
                fc, fc, fc, fc, fc, fc, fc, fc, fc, fc);
    }
}
