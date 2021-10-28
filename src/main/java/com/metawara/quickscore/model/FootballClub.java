package com.metawara.quickscore.model;

public class FootballClub {
    private int id;
    private String name;
    private double chanceOfWinning;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getChanceOfWinning() {
        return chanceOfWinning;
    }

    public void setChanceOfWinning(double chanceOfWinning) {
        this.chanceOfWinning = chanceOfWinning;
    }
}