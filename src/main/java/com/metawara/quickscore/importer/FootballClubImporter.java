package com.metawara.quickscore.importer;

import com.metawara.quickscore.model.FootballClub;

import java.util.Set;

public interface FootballClubImporter {
    Set<FootballClub> importClubs();
}
