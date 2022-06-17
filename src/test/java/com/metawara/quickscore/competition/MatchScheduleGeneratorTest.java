package com.metawara.quickscore.competition;

import com.metawara.quickscore.model.FootballClub;
import com.metawara.quickscore.model.match.MatchWeek;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = MatchScheduleGenerator.class)
@RunWith(MockitoJUnitRunner.class)
public class MatchScheduleGeneratorTest {

    List<FootballClub> clubs;

    @Before
    public void init() {
        clubs = prepareMockedClubs();
    }

    @Test
    public void simulateASeason_shouldProperlyGenerateFixtures() {
        List<MatchWeek> matchWeekHistory = MatchScheduleGenerator.generateMatchWeeksForDoubleRoundRobinTrmt(clubs);

        assertEquals((long) clubs.size() * clubs.size() - clubs.size(),
                matchWeekHistory.stream()
                        .mapToInt(x -> x.getWeekMatches().size())
                        .sum());

    }

    private List<FootballClub> prepareMockedClubs() {
        FootballClub fc = Mockito.mock(FootballClub.class);

        return List.of(fc, fc, fc, fc, fc, fc, fc, fc, fc, fc,
                fc, fc, fc, fc, fc, fc, fc, fc, fc, fc);
    }
}
