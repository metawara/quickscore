package com.metawara.quickscore.importer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.metawara.quickscore.display.SimpleResultsPrinter;
import com.metawara.quickscore.model.FootballClub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JSONFootballClubImporter implements FootballClubImporter {

    private static final Logger logger = LoggerFactory.getLogger(JSONFootballClubImporter.class);

    @Override
    public Set<FootballClub> importClubs() {
        Gson gson = new GsonBuilder().create();
        BufferedReader bufferedReader;
        Set<FootballClub> clubs = new HashSet<>();

        try {
            bufferedReader = new BufferedReader(new FileReader(new ClassPathResource("sampleroster.json").getFile()));
            FootballClub[] importedClubs = gson.fromJson(bufferedReader, FootballClub[].class);
            if (importedClubs.length != 0) {
                logger.debug("Looks like I managed to import something from a file...");
            }
            clubs = Set.of(importedClubs);
            logger.debug("Imported clubs: {}", clubs);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return clubs;
    }
}
