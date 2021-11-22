package com.metawara.quickscore.competition;

import com.metawara.quickscore.importer.FootballClubImporter;
import com.metawara.quickscore.match.manager.MatchManager;
import com.metawara.quickscore.model.FootballClub;
import com.metawara.quickscore.model.match.FCMatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DomesticLeagueManager {

    private static final Logger logger = LoggerFactory.getLogger(DomesticLeagueManager.class);

    List<FootballClub> participatingClubs;
    Map<Integer, List<FCMatch>> matchWeeks;

    private int weekCounter = 1;

    private final MatchManager matchManager;

    public DomesticLeagueManager(MatchManager matchManager, FootballClubImporter importer) {
        this.matchManager = matchManager;
        participatingClubs = importer.importClubs();
        matchWeeks = new HashMap<>();
    }

    void simulateWeek(){
        FootballClub [][] pairs = generatePairs();
        List<FCMatch> matches = new ArrayList<>();
        for (FootballClub[] pair : pairs) {
            matches.add(new FCMatch(pair[0], pair[1]));
        }

        for(FCMatch match: matches) {
            matchManager.manageMatch(match);
        }

        matchWeeks.put(weekCounter++, matches);
    }

    private FootballClub[][] generatePairs() {
        FootballClub[][] pairs = new FootballClub[participatingClubs.size() / 2][2];
        List<FootballClub> clubs = new ArrayList<>(participatingClubs);

        for(int i = 0; i < participatingClubs.size() / 2; i++){
            if(clubs.size() > 2){
                pairs[i][0] = clubs.get(0);
                pairs[i][1] = clubs.get(1);

                clubs.remove(0);
                clubs.remove(0);
            }
        }

        return pairs;
    }

    public List<FootballClub> getParticipatingClubs() {
        return participatingClubs;
    }

    public Map<Integer, List<FCMatch>> getMatchWeeks() {
        return matchWeeks;
    }

    public int getWeekCounter() {
        return weekCounter;
    }
}
