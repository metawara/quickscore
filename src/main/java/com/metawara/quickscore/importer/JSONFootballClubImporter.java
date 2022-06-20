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

/**
 * Imports football clubs from a JSON file.
 * A path to a JSON file is required while creating an instance.
 */
public class JSONFootballClubImporter implements FootballClubImporter {

    private static final Logger logger = LoggerFactory.getLogger(JSONFootballClubImporter.class);

    private String path;

    public JSONFootballClubImporter(String path) {
        this.path = path;
    }

    @Override
    public List<FootballClub> importClubs() {
        return importClubs(path);
    }

    private List<FootballClub> importClubs(String path) {
        logger.debug("Attempting to import clubs from a file with a path {}", path);
        Gson gson = new GsonBuilder().create();
        List<FootballClub> clubs = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new ClassPathResource(path).getFile()))) {
            clubs = convertArrayIntoList(gson.fromJson(bufferedReader, FootballClub[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return clubs;
    }

    private List<FootballClub> convertArrayIntoList(FootballClub[] importedClubs) {
        if (importedClubs.length != 0) {
            logger.debug("Looks like I managed to import something...");
        }
        List<FootballClub> clubs = List.of(importedClubs);
        logger.debug("Imported clubs: {}", clubs);
        return clubs;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
