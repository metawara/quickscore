package com.metawara.quickscore.service;

import com.metawara.quickscore.model.FootballClub;

import java.util.Optional;
import java.util.Set;

public class FootballClubService {

    private Set<FootballClub> footballClubs;

    public Optional<FootballClub> getFootballClubWithId(int id) {
        //TODO replace with stream
        for (FootballClub footballClub : footballClubs) {
            if (footballClub.getId() == id) {
                return Optional.of(footballClub);
            }
        }
        return Optional.empty();
    }
}