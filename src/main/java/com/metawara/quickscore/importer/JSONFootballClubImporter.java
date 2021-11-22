package com.metawara.quickscore.importer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.metawara.quickscore.model.FootballClub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONFootballClubImporter implements FootballClubImporter {

    private static final Logger logger = LoggerFactory.getLogger(JSONFootballClubImporter.class);

    @Override
    public List<FootballClub> importClubs() {
        return importClubs("sampleroster.json");
    }

    public List<FootballClub> importClubs(String jsonStr){
        logger.debug("Attempting to import clubs from a file with a path {}", jsonStr);
        Gson gson = new GsonBuilder().create();
        BufferedReader bufferedReader;
        List<FootballClub> clubs = new ArrayList<>();

        try {
            bufferedReader = new BufferedReader(new FileReader(new ClassPathResource(jsonStr).getFile()));
            clubs = convertArrayIntoList(gson.fromJson(bufferedReader, FootballClub[].class));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return clubs;
    }

    private List<FootballClub> convertArrayIntoList(FootballClub[] importedClubs) {
        if (importedClubs.length != 0) {
            logger.debug("Looks like I managed to import something from a String...");
        }
        List<FootballClub> clubs = List.of(importedClubs);
        logger.debug("Imported clubs: {}", clubs);
        return clubs;
    }
}
