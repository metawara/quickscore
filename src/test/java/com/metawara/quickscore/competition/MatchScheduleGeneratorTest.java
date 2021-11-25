package com.metawara.quickscore.competition;

import com.metawara.quickscore.model.FootballClub;
import com.metawara.quickscore.model.match.FCMatch;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = MatchScheduleGenerator.class)
@RunWith(MockitoJUnitRunner.class)
public class MatchScheduleGeneratorTest {

    @InjectMocks
    MatchScheduleGenerator matchScheduleGenerator;

    List<FootballClub> clubs;

    @Before
    public void init() {
        clubs = prepareMockedClubs();
    }

    @Test
    public void simulateASeason_shouldProperlyGenerateFixtures() {
        matchScheduleGenerator = new MatchScheduleGenerator();
        Map<Integer, List<FCMatch>> matchWeekHistory = matchScheduleGenerator.generateMatchWeeks(clubs);


        assertEquals((long) clubs.size() * clubs.size() - clubs.size(), matchWeekHistory
                .values().stream().mapToLong(List::size).sum());

    }

    private List<FootballClub> prepareMockedClubs() {
        FootballClub fc = Mockito.mock(FootballClub.class);

        return List.of(fc, fc, fc, fc, fc, fc, fc, fc, fc, fc,
                fc, fc, fc, fc, fc, fc, fc, fc, fc, fc);
    }
}
