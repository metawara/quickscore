package com.metawara.quickscore.importer;

import com.metawara.quickscore.model.FootballClub;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = JSONFootballClubImporter.class)
@RunWith(MockitoJUnitRunner.class)
public class JsonFootballClubImporterTest {

    public JSONFootballClubImporter importer = new JSONFootballClubImporter();

    @Test
    public void importFootballClubsFromAJson_shouldSucceed(){
        Set<FootballClub> clubs = importer.importClubs();
        assertEquals(4, clubs.size());
    }
}
