package com.metawara.quickscore.model;

public class FootballClub {
    private final String name;
    private final double chanceOfWinning;

    public FootballClub(String name, double chanceOfWinning) {
        this.name = name;
        this.chanceOfWinning = chanceOfWinning;
    }

    public String getName() {
        return name;
    }

    public double getChanceOfWinning() {
        return chanceOfWinning;
    }
}