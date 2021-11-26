package com.metawara.quickscore.model;

public class TableResults {
    FootballClub footballClub;
    int matchesPlayed = 0;
    int wins = 0;
    int draws = 0;
    int losses = 0;
    int points = 0;
    int goalsScored = 0;
    int goalsConceded = 0;

    public TableResults(FootballClub footballClub) {
        this.footballClub = footballClub;
    }

    public FootballClub getFootballClub() {
        return footballClub;
    }

    public void increaseMatchesPlayed() {
        matchesPlayed++;
    }

    public void updatePoints(int pointsScored){
        if (pointsScored == 3){
            wins++;
        } else if (pointsScored == 1){
            draws++;
        } else {
            losses++;
        }
        points = points + pointsScored;
    }

    public void updateGoalsScored(int gs){
        goalsScored = goalsScored + gs;
    }

    public void updateGoalsConceded(int gc){
        goalsConceded = goalsConceded + gc;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return "\n" + String.format("%1$" + 20 + "s", footballClub.getName()) + "\t\t\t MP:" + matchesPlayed
                + "\t W:" + wins + "\t D:" + draws + "\t L:" + losses
                + "\t P:" + points + "\t GS:" + goalsScored + "\t GC:" + goalsConceded;
    }
}
